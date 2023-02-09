import javafx.scene.input.DataFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AppInitializer {
    //database area
    static String[][] users = new String[3][2];

    public static void main(String[] args) {

        //input value
        Scanner input = new Scanner(System.in);
        boolean exitState=false;

        //program initializer
        String[] initializeQuestions = {
                "1) Do you want to login?",
                "2) Are you new to here?",
                "3) Do you want to exit here?"
        };


        while (!exitState){
            for (String question : initializeQuestions) {
                System.out.println(question);
            }

            int userInput = input.nextInt();

            switch (userInput) {
                case 1:
                    if (login()) {
                        openDashboard();
                    }
                    printUI("Application");
                    break;
                case 2:
                    if (register()) {
                        openDashboard();
                    }
                    break;
                case 3:
                    System.out.println("Good bye...!!");
                    return;

                default:
                    System.out.println("Wrong input");
                    return;// return
            }
        }
    }


    //print UI
    public static void printUI(String position){

        Date getDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        String date = dateFormat.format(getDate);
        String time = timeFormat.format(getDate);

        System.out.println("================="+date+"================="+time+"================="+position+"=================");
    }

    //login process
    public static boolean login() {
        printUI("Login");
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
        printUI("Register");
        if (users[users.length - 1][0] != null) {
            System.out.println("The database is already full");
            return false;
        }

        Scanner insertRegDetails = new Scanner(System.in);
        System.out.println("Enter your email");
        String email = insertRegDetails.nextLine();
        System.out.println("Enter your password");
        String pwd = insertRegDetails.nextLine();


        for (int i = 0; i < users.length; i++) {
            if (users[i][0] == null) {
                users[i][0] = email;
                users[i][1] = pwd;
                System.out.println("Record successfully added");
                return true;
            } else {
                if (users[i][0] != null && users[i][0].equalsIgnoreCase(email)) {
                    System.out.println("Already exist email.");
                    return false;
                }
            }

        }
        System.out.println("Something went wrong");
        return false;


    }

    //dashboard process
    public static void openDashboard() {
        printUI("Dashboard");
    }
}
