package com.pluralsight;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        ArrayList<Transactions> transactions = new ArrayList<Transactions>(); // create class for transactions
        // Creating the file:
        String fileName = "src/transactions.csv";
        Main app = new Main();  // the only thing that got it to not cause an error what chatgpt told me to add
        app.start();           // ^ ^
    }
        public void start() {
            boolean choosingExtension = true;// choosing an option
            Scanner scanner = new Scanner(System.in); // import scanner
        while (choosingExtension) {
            System.out.println("Home Screen: ");
            System.out.println("D) Add Deposit ");
            System.out.println("P) Make Payment (Debit) ");
            System.out.println("L) Display Ledger ");
            System.out.println("X) Exit ");
            String command = scanner.nextLine().toUpperCase();
                                                   // WB pg. 91
            switch (command) {
                case "D":
                    addADeposit(); //works if only static? jk switched it
                    break;
                case "P":
                    makeAPayment();
                    break;
                case "L":
                    displayTheLedger();
                    break;
                case "X":
                    choosingExtension = false; // bc im not choosing anymore extensions - exiting
                   System.out.println("Please try again. "); // FIX THIS need it to only say when wrong letter
                    break;
            }
        }
        System.out.println("Thank you, goodbye! ");
    }
    public void addADeposit() {// works if only static????
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the date (YYYY-MM-DD) ");
        String date = scanner.nextLine();
        System.out.println("Enter the time (HH:MM:SS) ");
        String time = scanner.nextLine();
        System.out.println("Enter the description ");
        String description = scanner.nextLine();
        System.out.println("Enter the vendor ");
        String vendor = scanner.nextLine();
        System.out.println("Enter the amount ");
        String amount = scanner.nextLine();

       // ArrayList<Transactions> transactions = new ArrayList<Transactions>(); // create class for transactions
        String fileName = "src/transactions.csv";
        String[] transactions = {};
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String transaction : transactions) {
                writer.write(transaction);
                writer.newLine();
            }
            System.out.println("File written successfully. ");
        } catch (IOException e) {
            System.out.println("An error occured while writing the file ");
            e.printStackTrace();
        }
///////////
    }
    public void makeAPayment() {


    }
    public void displayTheLedger() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ledger Options: ");
        System.out.println("A) Display transactions ");
        System.out.println("D) Display deposits ");
        System.out.println("P) Display payments ");
        System.out.println("R) Display reports ");
        System.out.println("H) Home page ");
        String choice = scanner.nextLine();






        System.out.println(" Reports: ");
        System.out.println("1) Month to date: ");
        System.out.println("2) Previous Month: ");
        System.out.println("3) Year to date: ");
        System.out.println("4) Previous year: ");
        System.out.println("5) Search by vendor ");
        System.out.println("0) Back to report page ");
        System.out.println("H) Back to home page ");
      //  String extension3 = scanner.nextLine();
    }
}