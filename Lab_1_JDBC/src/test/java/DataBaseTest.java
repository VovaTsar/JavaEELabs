import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Tsaruk Volodymyr
 * Testing class DataBaseTest for testing method of DataBase class
 */
class DataBaseTest {

    /**
     * Connection of DataBase
     */
    DataBase dataBase;
    /**
     * Object of Plane Class for testing
     */
    Plane firstPlane;
    /**
     * Object of Plane Class for testing
     */
    Plane secondPlane;

    /**
     * Initialization data for testing
     */
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        firstPlane = new Plane("TY-134", "Churchill", "Ferrari", 332232);
        secondPlane = new Plane("Boing777", "Chuck", "Typool", 2013246);
        dataBase = new DataBase();
    }

    /**
     * Test method Insert
     *
     * @result add some information in data base
     */
    @org.junit.jupiter.api.Test
    void insert() throws SQLException, ClassNotFoundException {
        try (Connection connection = dataBase.connect();
             Statement statement = connection.createStatement()) {
            System.out.println("Connected successfully!" + "\n");
            dataBase.create(statement, connection);
            dataBase.Insert(firstPlane);
            dataBase.Insert(secondPlane);
            ResultSet resultSet = dataBase.Select("Brand", firstPlane.getBrand());
            while (resultSet.next()) {
                assertEquals(firstPlane.getBrand(), resultSet.getString("Brand"));
                assertEquals(firstPlane.getCaptain(), resultSet.getString("Captain"));
                assertEquals(firstPlane.getEngine(), resultSet.getString("Engine"));
                assertEquals(firstPlane.getSeries(), resultSet.getInt("Series"));
            }
        }
    }

    /**
     * Test method Select
     *
     * @result return needed information from data base
     */
    @org.junit.jupiter.api.Test
    void select() throws SQLException, ClassNotFoundException {
        try (Connection connection = dataBase.connect();
             Statement statement = connection.createStatement()) {
            System.out.println("Connected successfully!" + "\n");
            dataBase.create(statement, connection);
            dataBase.Insert(secondPlane);
            ResultSet resultSet = dataBase.Select("Brand", secondPlane.getBrand());
            while (resultSet.next()) {
                assertEquals(secondPlane.getEngine(), resultSet.getString("Engine"));
                assertEquals(secondPlane.getSeries(), resultSet.getInt("Series"));
                assertEquals(secondPlane.getBrand(), resultSet.getString("Brand"));
                assertEquals(secondPlane.getCaptain(), resultSet.getString("Captain"));
            }
        }
    }

    /**
     * Test method Update
     *
     * @result update some information in data base
     */
    @org.junit.jupiter.api.Test
    void update() throws SQLException, ClassNotFoundException {
        try (Connection connection = dataBase.connect();
             Statement statement = connection.createStatement()) {
            System.out.println("Connected successfully!" + "\n");
            dataBase.create(statement, connection);
            dataBase.Insert(secondPlane);
            secondPlane.setBrand("Boing77");
            dataBase.Update(secondPlane);
            ResultSet resultSet = dataBase.Select("Brand", secondPlane.getBrand());
            while (resultSet.next()) {
                assertEquals(secondPlane.getBrand(), resultSet.getString("Brand"));
            }
        }
    }

    /**
     * Test method Delete
     *
     * @result delete some information in data base
     */
    @org.junit.jupiter.api.Test
    void delete() throws SQLException, ClassNotFoundException {
        try (Connection connection = dataBase.connect();
             Statement statement = connection.createStatement()) {
            System.out.println("Connected successfully!" + "\n");
            dataBase.create(statement, connection);
            dataBase.Insert(secondPlane);
            dataBase.Insert(firstPlane);
            dataBase.Delete(1);
            ResultSet resultSet = dataBase.Select("Brand", firstPlane.getBrand());
            int countWinners = 0;
            while (resultSet.next()) {
                countWinners++;
            }
            assertEquals(countWinners, 1);
        }
    }
}