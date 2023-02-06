import java.util.Scanner;

public class AppInitializer {
    //database area
    static String[][] users = new String[3][2];

    public static void main(String[] args) {

        //input value
        Scanner input = new Scanner(System.in);

        //program initializer
        String[] initializeQuestions = {
                "1) Do you want to login?",
                "2) Are you new to here?",
                "3) Do you want to exit here?"
        };

        for (String question : initializeQuestions) {
            System.out.println(question);
        }

        int userInput = input.nextInt();

        switch (userInput) {
            case 1:
                if (login()) {
                    openDashboard();
                }
                break;
            case 2:
                if (register()) {
                    openDashboard();
                }
                break;
            case 3:

                break;

            default:
                System.out.println("Wrong input");
                return;// return
        }

    }

    //login process
    public static boolean login() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your email");
        String email = input.nextLine();
        System.out.println("Enter your password");
        String pwd = input.nextLine();

        for (int i = 0; i < users.length; i++) {
            if (users[i][0] != null && users[i][0].equalsIgnoreCase(email)) {
                if (users[i][1].equalsIgnoreCase(pwd)) {
                    System.out.println("Welcome");
                    return true;
                }
            } else {
                System.out.println("Wrong user name or password");
                return false;
            }
        }
        System.out.println("404 not found");
        return false;
    }


    //register process
    public static boolean register() {
        if (users[users.length - 1][0] != null) {
            System.out.println("The database is already full");
            return false;
        }

        Scanner insertRegDet = new Scanner(System.in);
        System.out.println("Enter your email");
        String email = insertRegDet.nextLine();
        System.out.println("Enter your password");
        String pwd = insertRegDet.nextLine();

        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i][0] = email;
                users[i][1] = pwd;
                return true;
            } else {
                if (users[i][0].equalsIgnoreCase(email)) {
                    System.out.println("Already exist email.");
                    return false;
                }
            }

        }
        return false;


    }

    //dashboard process
    public static void openDashboard() {
    }
}
