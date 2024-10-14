package com.pluralsight;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // import scanner
        //ArrayList<Transactions> transactions = new ArrayList<Transactions>(); // create class for transactions
        // Creating the file:
        String fileName = "src/transactions.csv";
        String[] transactions = {"2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50",
                "2023-04-15|11:15:00|Invoice 1001 paid|Joe|1500.00"};
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

       // WB pg 91
        boolean choosingExtension = true; // choosing an option

        while (choosingExtension) {
            System.out.println("Home Page: ");
            System.out.println("D) Add Deposit ");
            System.out.println("P) Make Payment (Debit) ");
            System.out.println("L) Display Ledger ");
            System.out.println("X) Exit ");
            boolean command = scanner.nextLine().equalsIgnoreCase("d");

            switch (command) {
                case "D":
                    addADeposit();
                    break;
                case "P":
                    makeAPayment();
                    break;
                case "L":
                    displayTheLedger();
                    break;
                case "X":
                    choosingExtension = false; // bc im not choosing anymore extensions - exiting
                    System.out.println("Please try again. ");
                    break;
            }
        }
        System.out.println("Thank you, goodbye! ");






        System.out.println("Ledger Options: ");
        System.out.println("A) Display transactions ");
        System.out.println("D) Display deposits ");
        System.out.println("P) Display payments ");
        System.out.println("R) Display reports ");
        System.out.println("H) Home page ");
        String extension2 = scanner.nextLine();

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