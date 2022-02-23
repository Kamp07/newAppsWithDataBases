package dataBaseAppsExperiments;
import java.sql.*;

public class DataBase {

    private final String HOST = "127.0.0.1";
    private final String PORT = "3306";
    private final String DB_NAME = "javaTest_db";
    private final String LOGIN = "kampot";
    private final String PASS = "L337_kampot";

    private Connection connection = null;

    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String str = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
        Class.forName("com.mysql.jdbc.Driver");

        connection = DriverManager.getConnection(str, LOGIN, PASS);

        return connection;
    }

    public void isConnected() throws SQLException, ClassNotFoundException {
        connection = getDbConnection();
        System.out.println(connection.isValid(1000));
    }

    public void createTable(String tableName) throws SQLException, ClassNotFoundException
    {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName
                + " (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), password VARCHAR(100)) ENGINE = MYISAM;";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql);
    }

    public void insertArticle(String title, String text, String date, String author) throws SQLException, ClassNotFoundException
    {
        String sql = "INSERT INTO `articles` (title, text, date, author) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(sql);
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, text);
        preparedStatement.setString(3, date);
        preparedStatement.setString(4, author);

        preparedStatement.executeUpdate();
    }

    public void getArticles(String table) throws SQLException, ClassNotFoundException
    {
        String sql = "SELECT * FROM " + table + " WHERE `title` LIKE 'St%'";
        Statement statement = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next())
        {
            System.out.println(resultSet.getString("title"));
        }
    }

    public void updateArticles(String table) throws SQLException, ClassNotFoundException
    {
        String sql = "UPDATE `articles` SET `title` = ? WHERE `id` = 2";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(sql);
        preparedStatement.setString(1, "Update news");
        preparedStatement.executeUpdate();
    }

    public void deleteArticles(String table) throws SQLException, ClassNotFoundException
    {
        String sql = "DELETE FROM `articles` WHERE `id` = 2";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql);
    }

    public void deleteTable(String table) throws SQLException, ClassNotFoundException
    {
        String sql = "DROP TABLE " + table;
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql);
    }

    public void getOrders() throws SQLException, ClassNotFoundException {
        String sql = "SELECT javatest_db.users.name, javatest_db.items.title FROM javatest_db.orders \n" +
                "join javatest_db.users on javatest_db.orders.user_id = javatest_db.users.id\n" +
                "join javatest_db.items on javatest_db.orders.items_id = javatest_db.items.id\n" +
                "where javatest_db.items.category like 'hats';";
        Statement statement = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next())
        {
            System.out.println(resultSet.getString("name") + " - " + resultSet.getString("title"));
        }
    }

}
