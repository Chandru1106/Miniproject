import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class SocialPostApp implements AppInterface {
    private String dbUrl;
    private String username;
    private String password;
    private Connection connection;

    public SocialPostApp(String dbUrl, String username, String password) {
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

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter postid: ");
        int postid = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter content: ");
        String content = scanner.nextLine();

        System.out.print("Is published (true/false): ");
        String ispublished = scanner.nextLine();

        System.out.print("Enter platformname: ");
        String platformname = scanner.nextLine();

        System.out.print("Enter platformid: ");
        String platformid = scanner.nextLine();

        System.out.print("Enter scheduledat (YYYY-MM-DD HH:mm:ss): ");
        String scheduledat = scanner.nextLine();

        String insertQuery = "INSERT INTO social (postid, content, ispublished, platformname, platformid, scheduledat) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, postid);
            preparedStatement.setString(2, content);
            preparedStatement.setString(3, ispublished);
            preparedStatement.setString(4, platformname);
            preparedStatement.setString(5, platformid);
            preparedStatement.setString(6, scheduledat);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getScheduleDetails() {
        try {
            String selectQuery = "SELECT * FROM social";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int postid = resultSet.getInt("postid");
                String content = resultSet.getString("content");
                boolean ispublished = resultSet.getBoolean("ispublished");
                String platformname = resultSet.getString("platformname");
                String platformid = resultSet.getString("platformid");
                String scheduledat = resultSet.getString("scheduledat");

                System.out.println("Post ID: " + postid);
                System.out.println("Content: " + content);
                System.out.println("Is Published: " + ispublished);
                System.out.println("Platform Name: " + platformname);
                System.out.println("Platform ID: " + platformid);
                System.out.println("Scheduled At: " + scheduledat);
                System.out.println("---------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
