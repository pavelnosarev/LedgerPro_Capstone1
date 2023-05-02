package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;

import static java.time.LocalDate.now;

public class LedgerPro_Capstone1 {

    static ArrayList<Transaction> transactions = new ArrayList<>();
    static String TRANSACTIONS_FILE = "transactions.txt";
    static Scanner scanner = new Scanner(System.in);
    static LocalDate today = now();
//    static FileWriter writer;
//
//    static {
//        try {
//            writer = new FileWriter("transactions.txt");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void main(String[] args) throws FileNotFoundException{

//        FileWriter writer = new FileWriter("transactions.txt");

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
    private static void reportsMenu(Scanner scanner, ArrayList<Transaction> transactions){

        System.out.println("Which report do you need?");
        System.out.println("\t1 for month to date");
        System.out.println("\t2 for previous month month");
        System.out.println("\t3 for year to date");
        System.out.println("\t4 previous year");
        System.out.println("\t5 search by vendor");
        System.out.println("\t6 go back");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                List<Transaction> monthToDateTransactions =
                        getTransactionsBetweenDates(transactions,
                                now().minusMonths(1), now());
                for (Transaction transaction : monthToDateTransactions) {
                    System.out.println(transaction);
                }
                break;
            case 2:
                List<Transaction> previousMonthTransactions =
                        getTransactionsBetweenDates(transactions,
                                now().minusMonths(2),
                                now().minusMonths(1));
                for (Transaction transaction : previousMonthTransactions) {
                    System.out.println(transaction);
                }
                break;
            case 3:
                List<Transaction> yearToDateTransactions =
                        getTransactionsBetweenDates(transactions,
                                now().minusYears(1), now());
                for (Transaction transaction : yearToDateTransactions) {
                    System.out.println(transaction);
                }
                break;
            case 4:
                List<Transaction> previousYearTransactions =
                        getTransactionsBetweenDates(transactions,
                                now().minusYears(2),
                                now().minusYears(1));
                for (Transaction transaction : previousYearTransactions) {
                    System.out.println(transaction);
                }
                break;
            case 5:
                System.out.println("Enter the name of the vendor:");
                String vendorName = scanner.nextLine();
                List<Transaction> vendorTransactions =
                        getTransactionsByVendor(transactions, vendorName);
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
            if (transactionDate.compareTo(startDate) >= 0 && transactionDate.compareTo(endDate) <= 0) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
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


    private static void addDeposit(Scanner scanner, ArrayList<Transaction> transactions) {
        System.out.println("date of the deposit (YYYY-MM-DD):");
        String date = scanner.nextLine();

        System.out.println("time of deposit (HH:MM:SS):");
        String time = scanner.nextLine();

        System.out.println("description of deposit: ");
        String description = scanner.nextLine();

        System.out.println("Enter the vendor of the deposit:");
        String vendor = scanner.nextLine();

        System.out.println("Enter the amount of the deposit:");
        String amount = scanner.nextLine();

        Transaction transaction = new Transaction(date, time, description, vendor, Double.parseDouble(amount));
        transactions.add(transaction);
        System.out.println("Deposit added successfully.");
    }

    private static void makePayment(Scanner scanner, ArrayList<Transaction> transactions) {
        System.out.println("Enter the date of the payment (YYYY-MM-DD):");
        String date = scanner.nextLine();

        System.out.println("Enter the time of the payment (HH:MM:SS):");
        String time = scanner.nextLine();

        System.out.println("Enter the description of the payment:");
        String description = scanner.nextLine();

        System.out.println("Enter the vendor of the payment:");
        String vendor = scanner.nextLine();

        System.out.println("Enter the amount of the payment:");
        String amount = scanner.nextLine();

        Transaction transaction = new Transaction(
                date, time, description, vendor, -Double.parseDouble(amount));
        transactions.add(transaction);
        System.out.println("Payment made successfully.");
    }

    private static void viewLedger(ArrayList<Transaction> transactions) {
        System.out.println("All transactions:");
        System.out.println("Date | Time | Description | Vendor | Amount");
        transactions.forEach(transaction -> System.out.printf(
                "%s | %s | %s | %s | %.2f\n", transaction.getDate(),
                transaction.getTime(), transaction.getDescription(),
                transaction.getVendor(), transaction.getAmount()));

        System.out.println("");

        System.out.println("Deposits:");
        System.out.println("Date | Time | Description | Vendor | Amount");
        transactions.forEach(transaction -> {
            if (transaction.getAmount() > 0) {
                System.out.printf("%s | %s | %s | %s | %.2f\n",
                        transaction.getDate(), transaction.getTime(),
                        transaction.getDescription(), transaction.getVendor(),
                        transaction.getAmount());
            }
        });

        System.out.println("");
    }



}
