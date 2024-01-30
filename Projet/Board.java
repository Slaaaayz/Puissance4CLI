public class Board {
    // CLASSE QUI GERE NOTRE BOARD

    private String[][] grid = new String[6][7]; //Tableau pour représenter la grille

    public Board() { //Constructeur (rempli la grille d'emplacement vide (-))
        for (int x = 0; x < 6; x++){
            for (int y = 0; y < 7; y++){
                grid[x][y] = " - ";
            }
        }
    }

    public String[][] getGrille() { //Getter
        return grid;
    }

    public String toString() { //Affiche correctement la grille de jeu
        String aff = "";
        for (int x = 0; x < 6; x++){
            for (int y = 0; y < 7; y++){
                aff += grid[x][y] ;
            }
            aff += "\n";
        }
        aff += " 1  2  3  4  5  6  7";
        return aff;
    }

    public boolean Tie() { //Return true si il y a match nul
        for (int x = 0; x < 6; x++){
            for (int y = 0; y < 7; y++){
                if (grid[x][y] == " - ") {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean Win(String couleur) { //Return true si 4 jetons de la couleur passé en paramètres sont alignés consécutivement
        //Vérifie horizontalement
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length - 3; j++) {
                if (grid[i][j] == couleur && grid[i][j + 1] == couleur && grid[i][j + 2] == couleur && grid[i][j + 3] == couleur) {
                    return true;
                }
            }
        }
        //Vérifie verticalement
        for (int i = 0; i < grid.length - 3; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == couleur && grid[i + 1][j] == couleur && grid[i + 2][j] == couleur && grid[i + 3][j] == couleur) {
                    return true;
                }
            }
        }  
        //Vérifie les diagonales
        for (int i = 3; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length - 3; j++) {
                if (grid[i][j] == couleur && grid[i - 1][j + 1] == couleur && grid[i - 2][j + 2] == couleur && grid[i - 3][j + 3] == couleur) {
                    return true;
                }
            }
        }
        //Vérifie les autres diagonales
        for (int i = 0; i < grid.length - 3; i++) {
            for (int j = 0; j < grid[i].length - 3; j++) {
                if (grid[i][j] == couleur && grid[i + 1][j + 1] == couleur && grid[i + 2][j + 2] == couleur && grid[i + 3][j + 3] == couleur) {
                    return true;
                }
            }
        }
        return false;
    }
}
