import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://localhost/miniproject";
        String username = "root";
        String password = "chandru";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Mini Project App!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1) Add User");
            System.out.println("2) Schedule Post");
            System.out.println("3) Get Schedule");
            System.out.println("4) Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()

            AppInterface app;

            switch (choice) {
                case 1:
                    app = new UserApp(dbUrl, username, password);
                    
//                    UserApp userApp = (UserApp) app;
//                    System.out.print("Enter userid: ");
//                    int userid = scanner.nextInt();
//                    scanner.nextLine();
//                    userApp.setUserId(userid);
//
//                    System.out.print("Enter username: ");
//                    String username1 = scanner.nextLine();
//                    userApp.setUsername(username1);
//
//                    System.out.print("Enter userpassword: ");
//                    String userpassword = scanner.nextLine();
//                    userApp.setUserPassword(userpassword);
//
//                    System.out.print("Enter userphoneno: ");
//                    long userphoneno = scanner.nextLong();
//                    userApp.setUserPhoneNo(userphoneno);
                    break;
                case 2:
                    app = new SocialPostApp(dbUrl, username, password);
                    break;
                case 3:
                	app = new ScheduleDetailsApp(dbUrl, username, password);
                    break;
                case 4:
                    System.out.println("Good Bye...Exiting the application...");
                    return; // Exit the application
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    continue;
            }

            app.start();
        }
    }
}
