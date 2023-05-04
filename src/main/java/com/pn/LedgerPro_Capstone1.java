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
    private static void reportsMenu(Scanner scanner, ArrayList<Transaction> transactions){

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
            List<Transaction> transactions, String startDateStr, String endDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        List<Transaction> filteredTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = LocalDate.parse(transaction.getDate(), formatter);
            if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
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


    private static void addDeposit(
            Scanner scanner, ArrayList<Transaction> transactions)
            throws IOException {



        System.out.println("date of the deposit (DD-MM-YYYY):");
        String date = scanner.nextLine();

        System.out.println("time of deposit (HH:MM):");
        String time = scanner.nextLine();

        System.out.println("description of deposit: ");
        String description = scanner.nextLine();

        System.out.println("Enter the vendor of the deposit:");
        String vendor = scanner.nextLine();

        System.out.println("Enter the amount of the deposit:");
        double amount = Double.parseDouble(scanner.nextLine());

        Transaction transaction = new Transaction(date, time, description, vendor, Double.parseDouble(String.valueOf(amount)));
        transactions.add(transaction);
        System.out.println("Deposit added successfully.");
        try {
            writer = new FileWriter("./src/main/java/com/pn/transaction.txt", true);

            writer.write( date + "| " +  time + "| " + description + "| " + vendor + "| " + amount + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

        System.out.println("Enter the amount of the payment:");
        String amount = scanner.nextLine();

        Transaction transaction = new Transaction(
                date, time, description, vendor, -Double.parseDouble(amount));
        transactions.add(transaction);
        System.out.println("Payment made successfully.");

        try {
            writer = new FileWriter("./src/main/java/com/pn/transaction.txt", true);

            writer.write("\nPayment: " + date + " | " +  time + " | " + description + " | " + vendor + " | " + amount + " |");
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
    }

}









// read from file ONCE
//iterate through each line  + split in ()while loop
//create a new transaction + load in arrayList
//load inside transaction + add to ArrayList

// converting from string to a date

//        String date1= "2000-12-01"; // yyyy-mm-dd


//create a pattern
//DateTime Formatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd"); make sure to import datetimeformatter

// parse/covert the date using the created pattern
// LocalDate formattedDate1 = LocalDate.parse(date1, dateTimeFormatter1);
//print
//System.out.println(formattedDate1.getYear());







//    String date2 = "12-01-00"; //mm-dd-yy
//        String date3 = "01-12-00"; // dd=mm-yy




// from beginning of year

//if value is in given date range





//        String date = "2023-04-15";
//        String time = "10:13:25";
//        String dateTime = date + " " + time; // "2023-04-15 10:13:25"
//        // 1: Create a pattern for the date
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
////        2: Parse/convert the date using the created pattern
//        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, dateTimeFormatter);
//
//        System.out.println(localDateTime);/
//
//
//        / converting from a date to a string

// .compare

//        String date = "2023-05-03";
//        String time = "10:13:25";
//        String dateTime = date + " " + time; // "2023-04-15 10:13:25"
//        // 1: Create a pattern for the date
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
////        2: Parse/convert the date using the created pattern
//        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, dateTimeFormatter);

// LocalDateTime now = LocalDateTime.now();
// int comparison = transactionlDateTime.compareTo(now);
// if (comparison > 0) {
//          System.out.println("comes after");

//} else {'
// System.out.println("comes before");
//

//
//        System.out.println(now.compareTo(LocalDateTime));



///// MONTHTODATE
//   Month currentMonth = LocalDateTime.now().getMonth();

// LocalDateTime currentDateTIme = transactionDateTime.getMonth();
//System.out.println(transactionMonth.compareTo(currentMonth));