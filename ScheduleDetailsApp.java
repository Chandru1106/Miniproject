import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleDetailsApp  implements AppInterface{
    private String dbUrl;
    private String username;
    private String password;

    public ScheduleDetailsApp(String dbUrl, String username, String password) {
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
    }
    @Override
    public void start() {
    
        try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
            System.out.println("Connected to the database successfully!");

            String selectQuery = "SELECT * FROM social";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println("Schedule Details:");
                while (resultSet.next()) {
                    int postId = resultSet.getInt("postid");
                    String content = resultSet.getString("content");
                    String isPublished = resultSet.getString("ispublished");
                    String platformName = resultSet.getString("platformname");
                    String platformId = resultSet.getString("platformid");
                    String scheduledAt = resultSet.getString("scheduledat");

                    System.out.println("Post ID: " + postId);
                    System.out.println("Content: " + content);
                    System.out.println("Is Published: " + isPublished);
                    System.out.println("Platform Name: " + platformName);
                    System.out.println("Platform ID: " + platformId);
                    System.out.println("Scheduled At: " + scheduledAt);
                    System.out.println("-----------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
}
