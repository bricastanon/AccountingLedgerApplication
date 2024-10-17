package com.pluralsight;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Transactions {
    public String storeFile;
    public Transactions(String storeFile) {
        this.storeFile = storeFile;
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
        // Youtube - tried so mnay different ways wouldn't work until I appended true (to add to the existing file w/o deleting old info
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(transaction);
            writer.newLine();
            System.out.println("Deposit successful! ");
        } catch (IOException e) {
            System.out.println("Error making deposit  ");
            e.printStackTrace(); }
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(transaction);
            writer.newLine();
            System.out.println("Payment successful! ");
        } catch (IOException e) {
            System.out.println("Error making payment  ");
            e.printStackTrace(); }
    }

    ///// Ledger Methods
    public void displayTransactions() {
        // pulling the file and all transactions
        try (BufferedReader reader = new BufferedReader(new FileReader("src/transactions.csv"))) {
            String transaction;
            while ((transaction = reader.readLine()) != null) { // assigns then reads the called items
                System.out.println(transaction);
            }    // ^ null is to make sure there are no more line to read
        } catch (IOException e) {
            System.out.println("Error - try again. ");
            e.printStackTrace();
        }
    }
    public void displayDeposits() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/transactions.csv"))) {
            String transaction;
            while ((transaction = reader.readLine()) != null) { // assigns then reads the called items in while
                if (transaction.contains("|") != transaction.contains("|-$")) { // specifically asking for deposits and leaving out payments
                    System.out.println(transaction); }
            }
        } catch (IOException e) {
            System.out.println("Error - try again.  ");
            e.printStackTrace(); }
    }
    public void displayPayments() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/transactions.csv"))) {
            String transaction;
            while ((transaction = reader.readLine()) != null) { // gonna pull line and stop when theres no more value
                if (transaction.contains("|-$")) { // specifically asking for payment
                    System.out.println(transaction); }
            }
        } catch (IOException e) {
            System.out.println("Error - try again. ");
            e.printStackTrace(); }
    } // doing same thing as before

    //// Reports methods
    public void displayMonthToDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // building Month Year value in String Builder then stores both in Maps to get "October 2024"
        Map<String, StringBuilder> monthTransactions = new HashMap<>(); // **will only work with the layout I want using map
        try (BufferedReader reader = new BufferedReader(new FileReader("src/transactions.csv"))) {
            String transaction;
            while ((transaction = reader.readLine()) != null) {
                String[] info = transaction.split("\\|");  // this call all info while splitting it
                if (info.length == 5) { // makes sure its split into 5 "|"
                    LocalDate transactionDate = LocalDate.parse(info[0], formatter); // converting string format to localdate
                    if (!transactionDate.isAfter(today)) {  // includes from today or previous
                        String monthYear = transactionDate.getMonth() + " " + transactionDate.getYear();
                        monthTransactions.putIfAbsent(monthYear, new StringBuilder()); // creating month if not there
                        monthTransactions.get(monthYear).append(transaction).append("\n"); // creates transaction for month plus year
                    }
                }
            }
            for (Map.Entry<String, StringBuilder> entry : monthTransactions.entrySet()) { // printing by each month
                System.out.println("Transaction for " + entry.getKey()); // gathers each pairs per month
                System.out.println(entry.getValue().toString());
            }
        }   catch (IOException e) {
            System.out.println("Error with file ");
            e.printStackTrace();
        }
    }
    public void displayPreviousMonth() {
        LocalDate today = LocalDate.now();
        LocalDate firstOfPreviousMonth = today.minusMonths(1).withDayOfMonth(1);
        LocalDate firstOfCurrentMonth = today.withDayOfMonth(1);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        StringBuilder previousMonthTransactions = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/transactions.csv"))) {
            String transaction;
            while ((transaction = reader.readLine()) != null) {
                String[] info = transaction.split("\\|");
                if (info.length == 5) {
                    try {
                        LocalDate transactionDate = LocalDate.parse(info[0], dateFormatter);

                        // Check if the transaction is from the previous month
                        if (transactionDate.isAfter(firstOfPreviousMonth.minusDays(1)) &&
                                transactionDate.isBefore(firstOfCurrentMonth)) {
                            previousMonthTransactions.append(transaction).append("\n");
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid entry " + info[0]); }
                }
            }
            if (previousMonthTransactions.length() > 0) {   // output
                System.out.println("Transactions for " + firstOfPreviousMonth.getMonth() + " " + firstOfPreviousMonth.getYear()); // getting month and year
                System.out.println(previousMonthTransactions.toString());
            } else {
                System.out.println("No previous month transactions " + firstOfPreviousMonth.getMonth() + " " + firstOfPreviousMonth.getYear()); }
        }
        catch (IOException e) {
            System.out.println("Error with file ");
            e.printStackTrace();
        }
    }
    public void displayYearToDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Map<Integer, StringBuilder> yearTransactions = new HashMap<>(); // will only work using Map
        try (BufferedReader reader = new BufferedReader(new FileReader(storeFile))) {
            String transaction;
            while ((transaction = reader.readLine()) != null) {
                String[] info = transaction.split("\\|");
                if (info.length == 5) {
                    try {
                        LocalDate transactionDate = LocalDate.parse(info[0], dateFormatter);
                        int year = transactionDate.getYear();
                        yearTransactions.putIfAbsent(year, new StringBuilder());
                        yearTransactions.get(year).append(transaction).append("\n");
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid entry " + info[0]);
                    }
                }
            }
            for (Map.Entry<Integer, StringBuilder> entry : yearTransactions.entrySet()) {  // Output
                System.out.println("Transactions for " + entry.getKey());
                System.out.println(entry.getValue().toString());
            }
        } catch (IOException e) {
            System.out.println("Error with file ");
            e.printStackTrace();
        }
    }
       public void displayPreviousYear() {
           LocalDate today = LocalDate.now();
           LocalDate firstDayOfPreviousYear = today.minusYears(1).with(TemporalAdjusters.firstDayOfYear());
           LocalDate lastDayOfPreviousYear = today.minusYears(1).with(TemporalAdjusters.lastDayOfYear());
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           try (BufferedReader reader = new BufferedReader(new FileReader("src/transactions.csv"))) {
               String transaction;
               while ((transaction = reader.readLine()) != null) {
                   String[] info = transaction.split("\\|");
                   if (info.length == 5) {
                       LocalDate transactionDate = LocalDate.parse(info[0], formatter);
                       if (!transactionDate.isBefore(firstDayOfPreviousYear) &&
                       !transactionDate.isAfter(lastDayOfPreviousYear)){
                           System.out.println(transaction); }
                   }
               }
           } catch (IOException e) {
               System.out.println("Error with file ");
               e.printStackTrace();
           }
       }
    public void displaySearchByVendor() {
        Scanner scanner = new Scanner(System.in);  // doing same thing as before copy and update info from above
        System.out.println("Enter vendor: ");
        String vendor = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/transactions.csv"))) {
            String transaction;
            while ((transaction = reader.readLine()) != null) {
                if (transaction.contains(vendor)) { // specifically asking for vendor
                    System.out.println(transaction);
                }
            }
        } catch (IOException e) {
            System.out.println("Error - try again. ");
            e.printStackTrace();
        }
    }
}


















    /* public double addADeposit;
    public double makeAPayment;
    String displayTheLedger;


    public Transactions(double addADeposit, double makeAPayment, String displayTheLedger) {
        this.addADeposit = addADeposit;
        this.makeAPayment = makeAPayment;
        this.displayTheLedger = displayTheLedger;
    }
    public Transactions() {
        this.addADeposit = 0.00;
        this.makeAPayment = 0.00;
        this.displayTheLedger = "";
    }
    */
/* under file name*
    String[] transactions = {"2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50",
            "2023-04-15|11:15:00|Invoice 1001 paid|Joe|1500.00"};
        try (
    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
        for (String transaction : transactions) {
            writer.write(transaction);
            writer.newLine();
        }
        System.out.println("File written successfully. ");
    } catch (
    IOException e) {
        System.out.println("An error occured while writing the file ");
        e.printStackTrace();
    } */




/*
   public void displayMonthToDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try (BufferedReader reader = new BufferedReader(new FileReader("src/transactions.csv"))) {
            String line;
            // Using a simple StringBuilder to store the output for each month
            StringBuilder output = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) { // Ensure line has the expected format
                    LocalDate transactionDate = LocalDate.parse(parts[0], formatter);

                    // Check if the transaction is from this month or earlier
                    if (!transactionDate.isAfter(today)) {
                        String monthYear = transactionDate.getMonth() + " " + transactionDate.getYear();
                        output.append("Transactions for ").append(monthYear).append(":\n");
                        output.append(line).append("\n");
                    }
                }
            }

            // Print all transactions at once
            System.out.println(output.toString());
        } catch (IOException e) {
            System.out.println("Error reading transactions.");
            e.printStackTrace();
        }
    } */

//^^^^^^
  /*  Transactions for OCTOBER 2024:
            2024-10-15|11:10:02|
    Venmo transfer|Venmo|$500
    Transactions for OCTOBER 2024:
            2024-10-15|11:10:44|Rent|Entrada|-$2200
    Transactions for MAY 2024:
            2024-05-15|11:10:08|
    Festival ticket|EDSea|-$1800
    Transactions for OCTOBER 2024:
            2024-10-15|14:10:21|Payroll|
    True Freedom|$2000
    Transactions for AUGUST 2024:
            2024-08-15|14:10:76|Bill|SRP|-$64.87

   */




