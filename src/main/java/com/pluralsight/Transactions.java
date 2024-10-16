package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Transactions {


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
}



