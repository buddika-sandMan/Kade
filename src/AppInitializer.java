import javafx.scene.input.DataFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AppInitializer {
    //database area | can access all around the project
    //email, pwd
    static String[][] users = new String[3][2];

    //customer database
    //nic, name, address, salary
    static String[][] customer = new String[100][4];
    
    //item database
    //code, description, qtyOnHand, unitPrice
    static  String[][] item = new String[100][4];

    //order database
    //date, orderId, item, total, nic
    static String[][] order = new String[100][5];
    
    public static void main(String[] args) {

        //input value
        Scanner input = new Scanner(System.in);
        boolean exitState=false;

        //=====test item db
        item[0][0] = "001";
        item[0][1] = "Test Item";
        item[0][2] = "15";
        item[0][3] = "250";
        //=====test item db

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
                        printUI("Dashboard");
                        openDashboard();
                    }
                    printUI("Application");
                    break;
                case 2:
                    if (register()) {
                        printUI("Dashboard");
                        openDashboard();
                    }
                    printUI("Application");
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
            if (users[i][0] != null && users[i][0].equals(email)) {
                if (users[i][1].equals(pwd)) {
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

        Scanner input = new Scanner(System.in);

        String dashboardQuestions[] = {
                "1) Customer Management",
                "2) Item Management",
                "3) Order Management",
                "4) Logout"
        };


        while (true){
            for (String question:dashboardQuestions) {
                System.out.println(question);
            }

            int userInput = input.nextInt();

            switch (userInput){
                case 1:
                    customerManagement();
                    break ;
                case 2:
                    itemManagement();
                    break;
                case 3:
                    orderManagement();
                    break;
                case 4:
                    System.out.println("Good bye...!!!");
                    return;
                default:
                    System.out.println("Something went wrong...!!!");
                    return;
            }

        }

    }

    public static void itemManagement() {
    }

    public static void orderManagement() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Customer NIC");
        String nic = input.nextLine();

        String name, address;
        double salary;

        //customer find
        for (int i=0; i < customer.length; i++){
            if (customer[i][0]!=null){
                if(customer[i][0].equals(nic)){
                    System.out.println("======= Customer details =======");
                    name = customer[i][1];
                    address = customer[i][2];
                    salary = Double.parseDouble(customer[i][3]);
                    System.out.println("==============");
                }
            }
        }

        System.out.println("Enter Item Code");
        String code = input.nextLine();

        String description;
        double unitPrice=0;
        int qtyOnHand;

        //item find
        for (int i=0; i < item.length; i++){
            if (item[i][0]!=null){
                if(item[i][0].equals(nic)){
                    System.out.println("======= Item details =======");
                    description = item[i][1];
                    qtyOnHand = Integer.parseInt(item[i][2]);
                    unitPrice = Double.parseDouble(item[i][3]);

                    System.out.println("==============");
                }
            }
        }

        System.out.println("Enter Order Code");
        String orderID = input.nextLine();

        double total;
        int date;
        int orderId;


        //place an order
        for (int i=0; i< order.length; i++){
            if (order[i][0]!=null){
                if (order[i][0].equals(orderID)){
                    System.out.println("Order ID exists");
                    return;
                }else {

                    Date orderDate = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String selectedDate = dateFormat.format(orderDate);

                    order[i][0]=orderID;
                    order[i][1]=nic;
                    order[i][2]=code;
                    order[i][3]=selectedDate;
                    order[i][4]=String.valueOf(unitPrice);


                }
            }
        }
        System.out.println("Order Completed.");

    }

    public static void customerManagement() {
        //get customer input
        Scanner input = new Scanner(System.in);

        //manage customer questions
        String customerQuestions[] = {
                "1) Save Customer",
                "2) Find Customer",
                "3) Update Customer",
                "4) Delete Customer",
                "5) Find All Customer",
                "6) Back to Home",
        };

        while (true){
            for (String customerQuestion:customerQuestions) {
                System.out.println(customerQuestion);
            }

            int userInput = input.nextInt();

            switch (userInput){
                case 1:
                    saveCustomer();
                    break;
                case 2:
                    findCustomer();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                case 5:
                    findAllCustomers();
                    break;
                case 6:
                    openDashboard();
                    break;

                default:
                    return;
            }
        }


    }

    private static void findAllCustomers() {

        for (int i=0; i< customer.length; i++){
            if(customer[i][0]!=null){
                System.out.println("NIC : "+customer[i][0]+"\tNAME : "+customer[i][1]+"\tADDRESS : "+customer[i][2]+"\tSALARY : "+customer[i][3]);
            }else {
                return;
            }
        }
    }

    private static void deleteCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter delete Customer NIC");
        String nic = input.nextLine();

        for (int i=0; i < customer.length; i++){
            if (customer[i][0]!=null){
                if(customer[i][0].equals(nic)){

                    System.out.println("NIC : "+customer[i][0]);
                    System.out.println("Name : "+customer[i][1]);
                    System.out.println("Address : "+customer[i][2]);
                    System.out.println("Salary : "+customer[i][3]);

                    System.out.println("If you want delete "+customer[i][1]+", then please press \"Yes\"");
                    System.out.println("If you don't want delete "+customer[i][1]+", then please press \"No\"");

                    int option = input.nextInt();
                    if(option==1){
                        customer[i][0] = null;
                        customer[i][1] = null;
                        customer[i][2] = null;
                        customer[i][3] = null;
                        System.out.println("Customer deleted");
                        return;
                    }else if(option==2){
                        return;
                    }else{
                        System.out.println("Please enter a valid value");
                    }

                }
            }
        }
        System.out.println("Customer not found");
    }

    private static void updateCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert NIC to find the Customer");
        String nic = input.nextLine();

        for (int i=0; i < customer.length; i++){
            if (customer[i][0]!=null){
                if(customer[i][0].equals(nic)){

                    String newName, newNic, newAddress;
                    double newSalary;

                    System.out.println("NIC : "+customer[i][0]);
                    System.out.println("Name : "+customer[i][1]);
                    System.out.println("Address : "+customer[i][2]);
                    System.out.println("Salary : "+customer[i][3]);

                    System.out.println("==================");

                    System.out.println("Edit Customer Name");
                    newName = input.nextLine();
                    System.out.println("Edit Customer Address");
                    newAddress = input.nextLine();
                    System.out.println("Edit Customer Salary");
                    newSalary = input.nextDouble();

                    customer[i][1] = newName;
                    customer[i][2] = newAddress;
                    customer[i][3] = String.valueOf(newSalary);
                    System.out.println("Customer detail updated");
                    return;
                }
            }
        }
        System.out.println("Customer not found");
    }

    private static void findCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Customer NIC");
        String nic = input.nextLine();

        for (int i=0; i < customer.length; i++){
            if (customer[i][0]!=null){
                if(customer[i][0].equals(nic)){
                    System.out.println("======= Customer details under "+customer[i][0]+" NIC =======");
                    System.out.println("NIC : "+customer[i][0]);
                    System.out.println("Name : "+customer[i][1]);
                    System.out.println("Address : "+customer[i][2]);
                    System.out.println("Salary : "+customer[i][3]);
                    System.out.println("==============");
                    System.out.println("1) Do you want find another Customer?");
                    System.out.println("2) Back to menu");

                    int option = input.nextInt();

                    switch (option){
                        case 1:
                            findCustomer();
                        case 2:
                            return;
                        default:
                            return;
                    }

                }
            }
        }
        System.out.println("No found data under "+nic+" this NIC");
    }

    public static void saveCustomer(){
        Scanner input = new Scanner(System.in);

        while (true){
            String name, nic, address;
            double salary;

            System.out.println("Enter Customer NIC");
            nic = input.nextLine();
            System.out.println("Enter Customer Name");
            name = input.nextLine();
            System.out.println("Enter Customer Address");
            address = input.nextLine();
            System.out.println("Enter Customer Salary");
            salary = input.nextDouble();

            customerForLoop:
            for (int i = 0; i < customer.length; i++){
                if (customer[i][0]!=null){
                    if (customer[i][0].equals(nic)) {
                        System.out.println("Customer Already Exists");
                        return;
                    }
                }else {
                    customer[i][0] = nic;
                    customer[i][1] = name;
                    customer[i][2] = address;
                    customer[i][3] = String.valueOf(salary);

                    System.out.println("Customer details saved");
                    System.out.println("Do you want add another Customer?");
                    System.out.println("1) Do you want add another Customer?");
                    System.out.println("2) Back to menu");

                    int option = input.nextInt();

                    switch (option){
                        case 1:
                            saveCustomer();
                        case 2:
                            return;
                        default:
                            return;
                    }
                }
            }
        }
    }

}
