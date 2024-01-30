public class Player {
    //CLASSE QUI REPRESENTE NOTRE JOUEUR
    private final String name;
    private final String color;

    public Player(String m_name, String m_color) { //Constructeur
        color = m_color;
        name = m_name;
    }

    public String getColor() { //Getter
        return color;
    }

    public String getName() { //Getter
        return name;
    }
}