package com.pluralsight;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // Creating the file:
        ArrayList<Transactions> transactions = new ArrayList<Transactions>(); // create class for transactions
        String fileName = "src/transactions.csv"; // Kept up here cause the information wasn't adding
        Main app = new Main();  // the only thing that got it to not cause an error what chatgpt told me to add
        app.homeScreen();           // apparently it creates an instance of Main and calls the start method
    }
        public void homeScreen() {
            boolean choosingHome = true;// choosing an option to it running
            Scanner scanner = new Scanner(System.in); // import scanner
        while (choosingHome) {  // allows user to see the options
            System.out.println("Welcome! ");
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
                    System.out.println("Please try again. Invalid command. ");
            }
        }
        System.out.println("Thank you, goodbye! ");
    }
    public void addADeposit() {  // adding the information in the correct format
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd|HH:MM:SS");
        String dateTime = LocalDateTime.now().format(formatter);
        System.out.println("Enter the description ");
        String description = scanner.nextLine();
        System.out.println("Enter the vendor ");
        String vendor = scanner.nextLine();
        System.out.println("Enter the amount ");
        String amount = scanner.nextLine();

        String transaction = dateTime + "|" + description + "|" + vendor + "|$" + amount;
        String fileName = "src/transactions.csv";
        // Youtube
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(transaction);
            writer.newLine();
            System.out.println("Deposit successful! ");
        }
        catch (IOException e)
        {
            System.out.println("Error making deposit  ");
            e.printStackTrace();
        }
    }
    public void makeAPayment() { // Doing exact same thing as adding a deposit
    Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd|HH:MM:SS");
        String dateTime = LocalDateTime.now().format(formatter);
        System.out.println("Enter the description ");
        String description = scanner.nextLine();
        System.out.println("Enter the vendor ");
        String vendor = scanner.nextLine();
        System.out.println("Enter the amount ");
        String amount = scanner.nextLine();
        // in order to transfer info to file - chat
        String transaction = dateTime + "|" + description + "|" + vendor + "|-$" + amount;
        String fileName = "src/transactions.csv";
        // Youtube
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true)))
        {
            writer.write(transaction);
            writer.newLine();
            System.out.println("Payment successful! ");
        }
        catch (IOException e)
        {
            System.out.println("Error making payment  ");
            e.printStackTrace();
        }
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
                System.out.println("Please try again. Invalid command. ");
        }
    }
    public void displayTransactions() {
        // pulling the file and all transactions
        try (BufferedReader reader = new BufferedReader(new FileReader("src/transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) { // assigns then reads the called items
                System.out.println(line); }    // ^ null is to make sure there are no more line to read
        }
        catch (IOException e)
        {
            System.out.println("Error - try again. ");
            e.printStackTrace();
        }
    }
        public void displayDeposits() {
            try (BufferedReader reader = new BufferedReader(new FileReader("src/transactions.csv"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("|$") && !line.contains("|-$")) {
                        System.out.println(line); }
                }
            }
            catch (IOException e)
            {
                System.out.println("Error - try again.  ");
                e.printStackTrace();
            }
        }
        public void displayPayments() {
            try (BufferedReader reader = new BufferedReader(new FileReader("src/transactions.csv"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("|-$")) {
                        System.out.println(line); }
                }
            }
            catch (IOException e)
            {
                System.out.println("Error - try again. ");
                e.printStackTrace();
            }
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
            System.out.println("0) Back to report page ");
            System.out.println("H) Back to home page ");
            String extension3 = scanner.nextLine();

        }
        }
    }






