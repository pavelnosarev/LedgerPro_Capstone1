# LedgerPro_Capstone1


*requirements*
-
*display a menu of options to user : addDeposit, payment, viewLedger
, exit

*user will be able to make selection with those options

[addDeposit:  add deposit using date, time, description, vendor, amount]

[payment: add payment using date, time, description, vendor, amount]

[ledger: show ledger screen]

[reportsMenu: shows monthToMonth, previousMonth, yearToDate,
previousYear,searchByVendor, backToMainMenu ]

[exit]

*ledger needs to show everything in inventory.csv file (newest first), 
they can filter by [All, Deposits, Payments]

*reports will show reports [monthToMonth, previousMonth, yearToDate, 
previousYear, searchByVendor, backToMainMenu]

*will be able to search by [vendor] name, go back and forth from 
[reportPage] and [homePage]

# pseudocode
Start by importing the required packages and libraries.
    
    import java.util.*;
    import java.io.*;
    import java.time.*;

*public class LedgerPro

*Declare a static ArrayList of Transaction objects named transactions. 
This will hold all the transactions made by the user.
*Declare a static Scanner object named scanner. 
This will be used to get input from the user input.
*Declare a static FileWriter object named writer. 
This will be used to write transactions to my transaction.txt.
*Declare a static LocalDate object named today and initialize it to the current date. 
This will be used to get the current date for transactions.


    static ArrayList<Transaction> transactions = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static FileWriter writer;
    static LocalDate today = LocalDate.now();



## main
*Define a main method that throws an IOException.
*Inside the main method, call the loadTransactions method to load 
any previously saved transactions from a file into the transactions ArrayList.

      public static void main(String[] args) throws IOException {
        loadTransactions();

*Use a while loop to display a menu of options to the user and handle the user's choice with a switch statement:
a. If the user chooses "D", call the addDeposit method.
b. If the user chooses "P", call the makePayment method.
c. If the user chooses "L", call the viewLedger method.
d. If the user chooses "R", call the reportsMenu method.
e. If the user chooses "Q", print a message indicating that the app
is quitting and return from the main method.
f. If the user chooses any other option, display an error message 
and continue the loop.

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

### loadTransactions 
Define a loadTransactions method that reads transactions from a file
and adds them to the transactions ArrayList. 

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


### addDeposit
Define an addDeposit method that prompts the user for information
about a new deposit, creates a new Transaction object, adds it to the 
transactions ArrayList, and saves it to a file. This will allow the user 
to add money to their account.


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
            writer.write(transaction.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


### makePayment
Define a makePayment method that prompts the user for information
about a new payment, creates a new Transaction object with a negative 
amount, adds it to the transactions ArrayList, and saves it to a file. 
This will allow the user to make a payment.

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
            writer.write(transaction.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




### viewLedger
Define a viewLedger method that displays all transactions in the
transactions ArrayList. This will allow the user to see all the transactions
they have made.

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

### reportsMenu
Define a reportsMenu method that displays a menu of reporting options 
to the user and handles the user's choice with a switch statement:



    private static void reportsMenu(Scanner scanner, ArrayList<Transaction> transactions) {

        System.out.println("Which report do you need?");
        System.out.println("\t1 month to date");
        System.out.println("\t2 previous month");
        System.out.println("\t3 year to date");
        System.out.println("\t4 previous year");
        System.out.println("\t5 vendor");
        System.out.println("\t6 for main menu");

        int choice = Integer.parseInt(scanner.nextLine());



       
### 1
a. If the user chooses "1", display all transactions from the beginning
of the current month to the current date.

    switch (choice) {
            case 1:
                List<Transaction> monthToDateTransactions = getTransactionsBetweenDates(transactions,
                        LocalDate.now().withDayOfMonth(1), LocalDate.now());
                for (Transaction transaction : monthToDateTransactions) {
                    System.out.println(transaction);
                }
                break;
### 2
b. If the user chooses "2", display all transactions from the beginning
of the previous month to the end of the previous month.

            case 2:
                List<Transaction> previousMonthTransactions = getTransactionsBetweenDates(transactions,
                        LocalDate.now().minusMonths(1).withDayOfMonth(1),
                        LocalDate.now().minusMonths(1).withDayOfMonth(LocalDate.now().minusMonths(1).lengthOfMonth()));
                for (Transaction transaction : previousMonthTransactions) {
                    System.out.println(transaction);
                }
                break;
### 3
c. If the user chooses "3", display all transactions from the beginning
of the current year to the current date.


            case 3:
                List<Transaction> yearToDateTransactions = getTransactionsBetweenDates(transactions,
                        LocalDate.now().withDayOfYear(1), LocalDate.now());
                for (Transaction transaction : yearToDateTransactions) {
                    System.out.println(transaction);
                }
                break;
### 4
d. If the user chooses "4", display all transactions from the beginning
of the previous year to the end of the previous year.

            case 4:
                List<Transaction> previousYearTransactions = getTransactionsBetweenDates(transactions,
                        LocalDate.now().minusYears(1).withDayOfYear(1),
                        LocalDate.now().minusYears(1).withDayOfYear(LocalDate.now().minusYears(1).lengthOfYear()));
                for (Transaction transaction : previousYearTransactions) {
                    System.out.println(transaction);
                }
                break;
### 5
e. If the user chooses "5", prompt the user for a vendor name and display
all transactions with that vendor.

            case 5:
                System.out.println("Enter the name of the vendor:");
                String vendorName = scanner.nextLine();
                List<Transaction> vendorTransactions = getTransactionsByVendor(transactions, vendorName);
                for (Transaction transaction : vendorTransactions) {
                    System.out.println(transaction);
                }
                break;
### 6
f. If the user chooses "6", return from the method

            case 6:
                return;
            default:
                System.out.println("Nope, that is not an option.");
                break;
        }
    }



    

