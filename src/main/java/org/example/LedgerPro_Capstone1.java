package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.*;

public class LedgerPro_Capstone1 {

    private static final String TRANSACTIONS_FILE = "transactions.csv";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Transaction> transactions = new ArrayList<>();

        while (true) {
            System.out.println("What do you want?");
            System.out.println("\tD add deposit");
            System.out.println("\tP make payment");
            System.out.println("\tL view ledger");
            System.out.println("\tR reports menu");
            System.out.println("\tQ quit app");

            String choice = scanner.nextLine();

            switch (choice) {
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
                case "X":
                    System.out.println("quitting app, bye bye");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void reportsMenu(Scanner scanner, ArrayList <Transaction> transactions){

        System.out.println("Which report do you need?");
        System.out.println("\t1 for month to date");
        System.out.println("\t2 for previous month month");
        System.out.println("\t3 for year to date");
        System.out.println("\t4 previous year");
        System.out.println("\t5 search by vendor");
        System.out.println("\t6 go back");

        int subInput = scanner.nextInt();

        switch (subInput) {
            case 1:
                monthToDateReport(transactions); // call on transactions to show the monthtodate
                break;
            case 2:
                previousMonthReport(transactions);
                break;
            case 3:
                ytdReport(transactions);
                break;
            case 4:
                previousYearsReports(transactions);
                break;
            case 5:
                searchByVendor(transactions);
                break;
            case 6:
                return;
            default:
                System.out.println("nope try again");
        }

    }








    private static void addDeposit(Scanner scanner, ArrayList<Transaction> transactions) {
        System.out.println("Enter the date of the deposit (YYYY-MM-DD):");
        String date = scanner.nextLine();

        System.out.println("Enter the time of the deposit (HH:MM:SS):");
        String time = scanner.nextLine();

        System.out.println("Enter the description of the deposit:");
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

//    private static void monthToDateReport(ArrayList<Transaction> transactions) {
//        System.out.println("Please enter the start date of the report (YYYY-MM-DD):");
//        Scanner scanner = new scanner{};
//        String startDate = scanner.nextLine();
//        System.out.println("Please enter the end date of the report (YYYY-MM-DD):");
//        String endDate = scanner.nextLine();

//        List<Transaction> monthToDateTransactions =
//                getTransactionsBetweenDates(transactions, startDate, endDate);
//        for (Transaction transaction : monthToDateTransactions) {
//            System.out.println(transaction);
//        }
//    }


}
