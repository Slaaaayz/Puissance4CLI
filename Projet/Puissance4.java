import java.util.*;

public class Puissance4 {
    // JEU

    private Board board; //Grille du jeu

    public Puissance4() { //Constructeur
        board = new Board();
    }

    public Board getBoard() { //Getter
        return board;
    }

    public boolean IAMenu() { //Return true si le joueur veut jouer avec l'IA
        int choice = 0;
        while (choice < 1 || choice > 2) {
            Scanner scan1 = new Scanner(System.in);  
            System.out.println("Jouer contre une IA (1) ou contre un autre joueur (2) ?");
            choice = scan1.nextInt();
        }
        if (choice == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void Play() { // JEU
        // Demande les username et si on veut jouer contre l'IA
        Scanner scan1 = new Scanner(System.in);  
        System.out.println("Username 1 : ");
        String name1 = scan1.nextLine(); 
        Player player1 = new Player(name1, " R ");
        Player player2;
        boolean IA = IAMenu();
        if (IA) {
            player2 = new Player ("IA", " Y ");
        } else {
            Scanner scan2 = new Scanner(System.in);  
            System.out.println("Username 2 : ");
            String name2 = scan1.nextLine();
            player2 = new Player(name2, " Y ");
        }
        while (true) { // Tour par tour
            //Tour du joueur 1
            Turn(player1);
            //Vérification de victoire ou d'égalité
            if (board.Win(player1.getColor())) {
                System.out.println(board.toString());
                System.out.println(player1.getName() + " won ! GG"); 
                break;
            } else if (board.Tie()) {
                System.out.println("TIE, bien fonfon"); //Message d'égalité
                break;
            }
            if (IA) { //Si on joue contre l'IA on la fait joueur
                int iachoice = IAChoice()-1;
                while (!ApplyChoice(iachoice, player2)) {
                    iachoice = IAChoice()-1;
                }            
            } else { //Sinon on fait jouer le joueur 2
                Turn(player2);
            }
            //Vérification de victoire ou d'égalité
            if (board.Win(player2.getColor())) {
                System.out.println(board.toString());
                System.out.println(player2.getName() + " won ! GG");
                break;
            } else if (board.Tie()) {
                System.out.println("TIE, bien fonfon");
                break;
            }
        }
    }

    public void Turn(Player player) { //Tour du joueur "player"
        System.out.println(board.toString());
        int choice = 0;
        while (choice < 1 || choice > 7) {
            Scanner scan1 = new Scanner(System.in);  
            System.out.println(player.getName() + ", choose a row (betweeen 1 and 7):");
            choice = scan1.nextInt();
        }
        if (!ApplyChoice(choice-1, player)) {
            Turn(player);
        }
    }


    public int IAChoice() { //Retourne la colonne choisit par l'IA
        int columns = 7;
        int rows = 6;
        //Simule tt les possibilités du jaune
        for (int col = 0; col < columns; col++) {
            if (board.getGrille()[0][col].equals(" - ")) {
                for (int row = rows - 1; row >= 0; row--) {
                    if (board.getGrille()[row][col].equals(" - ")) {
                        board.getGrille()[row][col] = " Y ";
                        if (board.Win(" Y ")) { // Si ça mène à la victoire
                            board.getGrille()[row][col] = " - ";
                            return col + 1; //On choisit cette colonne
                        }
                        board.getGrille()[row][col] = " - ";
                        break;
                    }
                }
            }
        }
        //Simule tt les possibilités du rouge
        for (int col = 0; col < columns; col++) {
            if (board.getGrille()[0][col].equals(" - ")) {
                for (int row = rows - 1; row >= 0; row--) {
                    if (board.getGrille()[row][col].equals(" - ")) {
                        board.getGrille()[row][col] = " R ";
                        if (board.Win(" R ")) { //Si il risque de gagner
                            board.getGrille()[row][col] = " - ";
                            return col + 1;//On le bloque
                        }
                        board.getGrille()[row][col] = " - ";
                        break;
                    }
                }
            }
        }
        //Sinon on choisit de manière aléatoire
        return (int) (Math.random() * columns) + 1;
    }
    


    public boolean ApplyChoice(int choice, Player player) { //Return true si le choix est appliqué et applicable sinon return false
        if (board.getGrille()[0][choice] != " - ") {
            System.out.println("This row is already full !");
            return false;
        } 
        for (int i = 5; i >= 0; i--) {
            if (board.getGrille()[i][choice] == " - ") {
                board.getGrille()[i][choice] = player.getColor();
                return true;
            }
        }
        return false;
    }


}

