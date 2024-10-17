package com.pluralsight;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String correctPassword = "mu";
        String userInputPassword;
        do {
            System.out.print("Enter your password: ");
            userInputPassword = scanner.nextLine();
        } while (!userInputPassword.equals(correctPassword));
        System.out.println("Access granted. Welcome!");
        // Creating the file:
        ArrayList<Transactions> transactions = new ArrayList<Transactions>(); // create class for transactions
        String fileName = "src/transactions.csv"; // Kept up here cause the information wasn't adding
        Main app = new Main();  // the only thing that got it to not cause an error what chatgpt told me to add
        app.homeScreen();           // it creates an instance of Main and calls the start method
    }
    public void homeScreen() {
        boolean choosingHome = true;// choosing an option to it running
        Scanner scanner = new Scanner(System.in); // import scanner
        while (choosingHome) {  // allows user to see the options
            System.out.println("---Home Screen--- ");
            System.out.println("D) Add Deposit ");
            System.out.println("P) Make Payment (Debit) ");
            System.out.println("L) Display Ledger ");
            System.out.println("X) Exit ");
            String command = scanner.nextLine().toUpperCase(); // allows it to change it to uppercase
            // followed WB pg. 91
            switch (command) {
                case "D":
                    addADeposit(); // calls method
                    break;
                case "P":
                    makeAPayment();
                    break;
                case "L":
                    displayTheLedger();
                    break;
                case "X":
                    choosingHome = false; // bc im not choosing anymore options - exiting
                    break;
                default: // message for invalid option
                    System.out.println("Please try again. Invalid command. "); }
        } System.out.println("Thank you, goodbye! ");
    }
    public void addADeposit() {  // adding the information in the correct format
        String fileName = "src/transactions.csv";
        Transactions t = new Transactions(fileName);
        t.addADeposit();
    }
    public void makeAPayment() { // Doing exact same thing as adding a deposit
        String fileName = "src/transactions.csv";
        Transactions t = new Transactions(fileName);
        t.makeAPayment();
    }
    public void displayTheLedger() {
        Scanner scanner = new Scanner(System.in);
        boolean choosingLedger = true; // choosing an option to it running
        System.out.println("Ledger Options: ");
        System.out.println("A) Display transactions ");
        System.out.println("D) Display deposits ");
        System.out.println("P) Display payments ");
        System.out.println("R) Display reports ");
        System.out.println("H) Home page ");
        String command = scanner.nextLine().toUpperCase();
        switch (command) {
            case "A":
                displayTransactions();
                break;
            case "D":
                displayDeposits();
                break;
            case "P":
                displayPayments();
                break;
            case "R":
                displayReports();
                break;
            case "H":
                choosingLedger = false; // bc im not choosing anymore options - exiting
                break;
            default:
                System.out.println("Please try again. Invalid command. "); }
    }
    public void displayTransactions() {
        String fileName = "src/transactions.csv";
        Transactions t = new Transactions(fileName);
        t.displayTransactions();
    }

    public void displayDeposits() {
        String fileName = "src/transactions.csv";
        Transactions t = new Transactions(fileName);
        t.displayDeposits();
    }
    public void displayPayments() {
        String fileName = "src/transactions.csv";
        Transactions t = new Transactions(fileName);
        t.displayPayments();
    }
    public void displayReports() {
        Scanner scanner = new Scanner(System.in);
        boolean choosingReport = true;
        while (choosingReport) {
            System.out.println(" Reports: ");
            System.out.println("1) Month to date: ");
            System.out.println("2) Previous Month: ");
            System.out.println("3) Year to date: ");
            System.out.println("4) Previous year: ");
            System.out.println("5) Search by vendor ");
            System.out.println("0) Back to ledger page ");
            System.out.println("H) Back to home page ");
            String command = scanner.nextLine().toUpperCase();
            switch (command) {
                case "1":
                    displayMonthToDate();
                    break;
                case "2":
                    displayPreviousMonth();
                    break;
                case "3":
                    displayYearToDate();
                    break;
                case "4":
                    displayPreviousYear();
                    break;
                case "5":
                    displaySearchByVendor();
                    break;
                case "0":
                    displayTheLedger();
                    choosingReport = false; // exiting not choosing anymore
                    break;
                case "H":
                    homeScreen(); // same thing but add this
                    choosingReport = false; // exiting not choosing anymore
                    break;
                default:
                    System.out.println("Please try again. Invalid command. "); }
        }
    }
    public void displayMonthToDate() {
        String fileName = "src/transactions.csv";
        Transactions t = new Transactions(fileName);
        t.displayMonthToDate();
    }
    public void displayPreviousMonth() { // Moved to class - too much
       String fileName = "src/transactions.csv";
       Transactions t = new Transactions(fileName);
       t.displayPreviousMonth();
    }
    public void displayYearToDate() {
        String fileName = "src/transactions.csv";
        Transactions t = new Transactions(fileName);
        t.displayYearToDate();
    }
    public void displayPreviousYear() {
        String fileName = "src/transactions.csv";
        Transactions t = new Transactions(fileName);
        t.displayPreviousYear();
    }
    public void displaySearchByVendor() {
        String fileName = "src/transactions.csv";
        Transactions t = new Transactions(fileName);
        t.displaySearchByVendor();
    }
    }








