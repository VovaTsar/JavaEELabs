import java.sql.*;

/**
 * @author Tsaruk Volodymyr
 * Class DataBase that realized basic operation of DataBase database
 */
public class DataBase {
    private final String url = "jdbc:mysql://localhost:3306/JDBC?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    /**
     * Login for connection to database
     */
    private final String user = "root";
    /**
     * Password for connection to database
     */
    private final String password = "dfczdfcz25";
    /**
     * Name driver for connection to database
     */
    private final String nameDriver = "com.mysql.cj.jdbc.Driver";


    /**
     * Connection of DataBase database
     */
    private Connection connection;
    /**
     * Constructor for creating object about plane
     */
    public DataBase() {
    }

    /**
     * Method for creating database
     *
     * @param statement  - statement of my database
     * @param connection - Connection of DataBase database
     */

    public void create(Statement statement, Connection connection) throws SQLException {
        this.connection = connection;
        statement.executeUpdate("DROP TABLE plane;");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS plane (id INT NOT NULL AUTO_INCREMENT, Brand VARCHAR (30) NOT NULL, Captain VARCHAR (30) NOT NULL , Engine VARCHAR (30) NOT NULL, Series INT NOT NULL, PRIMARY KEY(id));");

    }

    /**
     * Connection to database
     *
     * @return connection to database
     */
    public Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(nameDriver);
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Operation INSERT for MYSQL database
     *
     * @param plane - plane
     */
    public void Insert(Plane plane) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO plane (Brand, Captain, Engine, Series) VALUES (?,?,?,?);");
        preparedStatement.setString(1, plane.getBrand());
        preparedStatement.setString(2, plane.getCaptain());
        preparedStatement.setString(3, plane.getEngine());
        preparedStatement.setInt(4, plane.getSeries());
        preparedStatement.executeUpdate();
    }


    /**
     * Operation SELECT for MYSQL database
     *
     * @param column     - column of plane
     * @param selectName - name of plane that selected
     * @return information of chosen planes
     */
    public ResultSet Select(String column, String selectName) throws SQLException {
        String querry = String.format("SELECT * FROM plane WHERE %s = ?;", column);
        PreparedStatement preparedStatement = connection.prepareStatement(querry);
        preparedStatement.setString(1, selectName);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    /**
     * Operation UPDATE for MYSQL database
     *
     * @param plane - plane
     */
    public void Update(Plane plane) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE plane SET Brand = ?, Captain = ?, Engine = ? WHERE Series = ?;");
        preparedStatement.setString(1, plane.getBrand());
        preparedStatement.setString(2, plane.getCaptain());
        preparedStatement.setString(3, plane.getEngine());
        preparedStatement.setInt(4, plane.getSeries());
        preparedStatement.executeUpdate();
    }

    /**
     * Operation DELETE for MYSQL database
     *
     * @param id - id of plane
     */
    public void Delete(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM plane WHERE ID = ?;");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}
