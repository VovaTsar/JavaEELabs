import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    private final String password = "root";
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
    public DataBase()  {



    }



    public void create() throws SQLException, ClassNotFoundException {
        try (Connection connect = this.connect()) {
            connection = connect;
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE plane;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS plane (id INT NOT NULL AUTO_INCREMENT, Brand VARCHAR (30) NOT NULL, Captain VARCHAR (30) NOT NULL , Engine VARCHAR (30) NOT NULL, Series VARCHAR (30) NOT NULL, PRIMARY KEY(id));");
        }
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
    public void insert(Plane plane) throws SQLException, ClassNotFoundException {
        try (Connection connect = this.connect()) {
            PreparedStatement preparedStatement = connect.prepareStatement("INSERT INTO plane (Brand, Captain, Engine, Series) VALUES (?,?,?,?);");
            preparedStatement.setString(1, plane.getBrand());
            preparedStatement.setString(2, plane.getCaptain());
            preparedStatement.setString(3, plane.getEngine());
            preparedStatement.setString(4, plane.getSeries());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Прочитать объект из базы данных
     * @return возвращает список элементов, подходящий под пару parameter-value
     */
    public ArrayList<Plane> selectAll() throws SQLException, ClassNotFoundException {
        try (Connection connect = this.connect()) {
            ArrayList<Plane> l =new ArrayList<>();
            connection = connect;
            PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM plane;");
            ResultSet resultSet = preparedStatement.executeQuery();
            l.clear();
            while (resultSet.next())
                l.add(new Plane(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
            return l;
        }
    }


    /**
     * Operation SELECT for MYSQL database
     *
     * @param column     - column of plane
     * @param selectName - name of plane that selected
     * @return information of chosen planes
     */
    public ResultSet select(String column, String selectName) throws SQLException {
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
    public void update(Plane plane) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE plane SET Brand = ?, Captain = ?, Engine = ? WHERE Series = ?;");
        preparedStatement.setString(1, plane.getBrand());
        preparedStatement.setString(2, plane.getCaptain());
        preparedStatement.setString(3, plane.getEngine());
        preparedStatement.setString(4, plane.getSeries());
        preparedStatement.executeUpdate();
    }

    /**
     * Operation DELETE for MYSQL database
     *
     * @param id - id of plane
     */
    public void delete(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM plane WHERE ID = ?;");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}
