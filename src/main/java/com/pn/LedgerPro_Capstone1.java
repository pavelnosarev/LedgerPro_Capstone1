package com.pn;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.time.*;

import static java.time.LocalDate.now;


public class LedgerPro_Capstone1 {

    static ArrayList<Transaction> transactions = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static FileWriter writer;
    static LocalDate today = LocalDate.now();

    public static void main(String[] args) throws IOException {
        loadTransactions();
        while (true) {
            System.out.println("What do you want?");
            System.out.println("\tD add deposit");
            System.out.println("\tP make payment");
            System.out.println("\tL view ledger");
            System.out.println("\tR reports menu");
            System.out.println("\tQ quit app");

            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "D":
                    addDeposit(scanner, transactions);
                    break;
                case "P":
                    makePayment(scanner, transactions);
                    break;
                case "L":
                    viewLedger(transactions);
                    break;
                case "R":
                    reportsMenu(scanner, transactions);
                    break;
                case "Q":
                    System.out.println("quitting app, bye bye");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void reportsMenu(Scanner scanner, ArrayList<Transaction> transactions) {

        System.out.println("Which report do you need?");
        System.out.println("\t1 for month to date");
        System.out.println("\t2 for previous month");
        System.out.println("\t3 for year to date");
        System.out.println("\t4 previous year");
        System.out.println("\t5 search by vendor");
        System.out.println("\t6 go back");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                List<Transaction> monthToDateTransactions = getTransactionsBetweenDates(transactions,
                        LocalDate.now().withDayOfMonth(1), LocalDate.now());
                for (Transaction transaction : monthToDateTransactions) {
                    System.out.println(transaction);
                }
                break;
            case 2:
                List<Transaction> previousMonthTransactions = getTransactionsBetweenDates(transactions,
                        LocalDate.now().minusMonths(1).withDayOfMonth(1),
                        LocalDate.now().minusMonths(1).withDayOfMonth(LocalDate.now().minusMonths(1).lengthOfMonth()));
                for (Transaction transaction : previousMonthTransactions) {
                    System.out.println(transaction);
                }
                break;
            case 3:
                List<Transaction> yearToDateTransactions = getTransactionsBetweenDates(transactions,
                        LocalDate.now().withDayOfYear(1), LocalDate.now());
                for (Transaction transaction : yearToDateTransactions) {
                    System.out.println(transaction);
                }
                break;
            case 4:
                List<Transaction> previousYearTransactions = getTransactionsBetweenDates(transactions,
                        LocalDate.now().minusYears(1).withDayOfYear(1),
                        LocalDate.now().minusYears(1).withDayOfYear(LocalDate.now().minusYears(1).lengthOfYear()));
                for (Transaction transaction : previousYearTransactions) {
                    System.out.println(transaction);
                }
                break;
            case 5:
                System.out.println("Enter the name of the vendor:");
                String vendorName = scanner.nextLine();
                List<Transaction> vendorTransactions = getTransactionsByVendor(transactions, vendorName);
                for (Transaction transaction : vendorTransactions) {
                    System.out.println(transaction);
                }
                break;
            case 6:
                return;
            default:
                System.out.println("Nope, that is not an option.");
                break;
        }
    }

    public static List<Transaction> getTransactionsBetweenDates(
            List<Transaction> transactions, LocalDate startDate, LocalDate endDate) {
        List<Transaction> filteredTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = LocalDate.parse(transaction.getDate());
            if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    private static void addDeposit(Scanner scanner, ArrayList<Transaction> transactions) {
        System.out.println("Enter the date of the deposit (DD-MM-YYYY):");
        String date = scanner.nextLine();

        System.out.println("Enter the time of the deposit (HH:MM:SS):");
        String time = scanner.nextLine();

        System.out.println("Enter the description of the deposit:");
        String description = scanner.nextLine();

        System.out.println("Enter the vendor of the deposit:");
        String vendor = scanner.nextLine();

        double amount = 0.0;
        boolean validAmount = false;
        while (!validAmount) {
            System.out.println("Enter the amount of the deposit:");
            String amountStr = scanner.nextLine();
            try {
                amount = Double.parseDouble(amountStr);
                validAmount = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
            }
        }
        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(transaction);
        System.out.println("Deposit added successfully.");
        try {
            writer = new FileWriter("./src/main/java/com/pn/transaction.txt", true);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}

    public static List<Transaction> getTransactionsByVendor(
            List<Transaction> transactions, String vendorName) {
        List<Transaction> filteredTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getVendor().equals(vendorName)) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }


    private static void makePayment(Scanner scanner, ArrayList<Transaction> transactions) {
        System.out.println("Enter the date of the payment (DD-MM-YYYY):");
        String date = scanner.nextLine();

        System.out.println("Enter the time of the payment (HH:MM:SS):");
        String time = scanner.nextLine();

        System.out.println("Enter the description of the payment:");
        String description = scanner.nextLine();

        System.out.println("Enter the vendor of the payment:");
        String vendor = scanner.nextLine();

        double amount = 0.0;
        boolean validAmount = false;
        while (!validAmount) {
            System.out.println("Enter the amount of the payment:");
            String amountStr = scanner.nextLine();
            try {
                amount = Double.parseDouble(amountStr);
                validAmount = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
            }
        }
        Transaction transaction = new Transaction(date, time, description, vendor, -amount);
        transactions.add(transaction);
        System.out.println("Payment made successfully.");
        try {
            writer = new FileWriter("./src/main/java/com/pn/transaction.txt", true);

            writer.write("\nPayment: " + date + " | " + time + " | " + description + " | " + vendor + " | " + amount + " |");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void viewLedger(ArrayList<Transaction> transactions) {
        System.out.println("All transactions:");
        System.out.println("Date | Time | Description | Vendor | Amount");
        transactions.forEach(transaction -> System.out.printf(
                "%s | %s | %s | %s | %.2f\n", transaction.getDate(),
                transaction.getTime(), transaction.getDescription(),
                transaction.getVendor(), transaction.getAmount()));

        System.out.println();

        System.out.println("Deposits:");
        System.out.println("Date | Time | Description | Vendor | Amount");
        boolean hasDeposits = false;
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                System.out.printf("%s | %s | %s | %s | %.2f\n",
                        transaction.getDate(), transaction.getTime(),
                        transaction.getDescription(), transaction.getVendor(),
                        transaction.getAmount());
                hasDeposits = true;
            }
        }
        if (!hasDeposits) {
            System.out.println("No deposits found.");
        }

        System.out.println();
    }
    public static void loadTransactions() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/java/com/pn/transaction.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] transactionData = line.split("\\|");
            Transaction transaction = new Transaction(
                    transactionData[0], transactionData[1], transactionData[2], transactionData[3], Double.parseDouble(transactionData[4]));
            transactions.add(transaction);

        }
        reader.close();
    }}

