import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class App {
    private String dbUrl;
    private String username;
    private String password;
    protected Connection connection;

    public App(String dbUrl, String username, String password) {
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
        try {
            this.connection = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    abstract void start();
}
