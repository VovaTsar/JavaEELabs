import java.sql.*;

/**
 * @author Tsaruk Volodymyr
 * Main Class that realized lab work
 */
public class Main {
    /**
     * Main function
     *
     * @param args arguments of main function
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DataBase dataBase = new DataBase();
        try (Connection connection = dataBase.connect();
             Statement statement = connection.createStatement()) {
            System.out.println("Connected successfully!" + "\n");
            dataBase.create(statement, connection);
            Plane firstPlane = new Plane("TY-134", "Churchill", "Ferrari", 332232);
            Plane secondPlane = new Plane("Boing777", "Chuck", "Typool", 2013246);
            dataBase.Insert(firstPlane);
            dataBase.Insert(secondPlane);
            secondPlane.setCaptain("Black");
            dataBase.Update(secondPlane);
            dataBase.Delete(1);
        }
    }
}
