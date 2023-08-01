import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

class UserApp extends App implements AppInterface {
    private int userid;
    private String username;
    private String userpassword;
    private long userphoneno;

    public UserApp(String dbUrl, String username, String password) {
        super(dbUrl, username, password);
    }

    // Setters for encapsulation
    public void setUserId(int userid) {
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserPassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public void setUserPhoneNo(long userphoneno) {
        this.userphoneno = userphoneno;
    }

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter userid: ");
        int userid = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter username: ");
        String username1 = scanner.nextLine();

        System.out.print("Enter userpassword: ");
        String userpassword = scanner.nextLine();

        System.out.print("Enter userphoneno: ");
        long userphoneno = scanner.nextLong();

        String insertQuery = "INSERT INTO user (userid, username1, userpassword, userphoneno) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, userid);
            preparedStatement.setString(2, username1);
            preparedStatement.setString(3, userpassword);
            preparedStatement.setLong(4, userphoneno);

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
}
