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

* import java.io.File;
* import java.io.FileWriter;
* import java.util.Scanner;
* import java.io.FileNotFoundException;
* import java.IOException;
* import java.util.ArrayList;

*public class LedgerPro 

    private static final String TRANSACTION_FILE ="transactions.csv"; 
[to read the csv file] 

  * create a main class inside our LedgerPro  : 

    public static void main(String[] args) throws  IOException { } 
  
  * create Scanner scanner = new Scanner (System.in); [takes in user input];
    * need an ArrayList to store the items :
        ArrayList<Transaction>transactions = new ArrayList <>() ;
  * need to use a do {} while loop to show the menu for my users 
  ### SOUT is shortcut for System.out.println()
maybe do a while(true)]?

#### menu
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Transaction> transactions = new ArrayList<>();


    while (true){  
            System.out.println("here are our options: ");
            System.out.println("\tD add deposit");
            System.out.println("\tP make payment");
            System.out.println("\tL view ledger");
            System.out.println("\tR reports menu");
            System.out.println("\tQ quit app");

        String input = scanner.nextLine(); // variable for user input through scanner 

  * switch statement to allow different paths depending on user input

### switch
  
  *        switch (input) {
                case "D":
                    addDeposit(scanner, transactions);
                    break;
                case "P":
                    payment(scanner,transactions);
                    break;
                case "L":
                    viewLedger(transactions);
                    break;
                case "Q":
                    sout("quitting application");
                    return;
                default:
                     System.out.println("nope that's not an option try again");

                }
            }
        }
  * need methods for addDeposit, payment, viewLedger, 
    * and saving transactions to my 'transactions.csv'

[addDeposit]

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
[payment]

    private static void payment(Scanner scanner, ArrayList<Transaction> transactions) {
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

        Transaction transaction = new Transaction(date, time, description, vendor, 
        -Double.parseDouble(amount)); [subtract from the double(args is amount)]
        transactions.add(transaction);
        System.out.println("Payment made successfully.");
    }

[viewLedger]

    private static void viewLedger(ArrayList<Transaction> transactions) {
        System.out.println("All transactions:");
        System.out.println("Date | Time | Description | Vendor | Amount");
        for (Transaction transaction : transactions) {
            System.out.printf("%s | %s | %s | %s | %.2f\n", transaction.getDate(), 
            transaction.getTime(), transaction.getDescription(), 
            transaction.getVendor(), transaction.getAmount());
        }

        System.out.println("");
    }




[passing through our method ]
      [passes user input into our arraylist of Transaction objects]
      sout ("enter date for deposit (yyyy-mm-dd):  ");
      String date = scanner.nextLine();
[do the same for time(hh:mm:ss), description, vendor, amount]
[also need a .add]? need to lookup how to? or ask greg?

    transaction.add(new Transaction(date, time,description, 
        vendor, amount));
    sout ("deposit added");
    


    }
    private static void payment(Scanner scanner, ArrayList<
    Transaction> transactions){ 

[same as the above method, must add println and String scanner 
for date, time, description, vendor, amount, and a way to add the
payment with .add? and a payment made message]

    private static void viewLedger (ArrayList<Transaction>
    transactions){}

[only need to pass through the arraylist for viewing the list of objects]
    
    sout("Transactions :");
    sout("Date | Time | Description | Vendor | Amount");

[need to iterate through users transactions, will use a : lambda 
and a for loop, using the : allows for me to pass the argument]

[ie (args for function - Transaction transaction : transactions)
{curly braces will hold my params for the args I placed - 
- use printf here to force specific print style and format}]
  
        for( Transaction transaction : transactions) {
          souf
      ("%s | %s | %s  | %s | %.2f\n",
         transaction.getDate(), transaction.getTime(), transaction.getDescription(),
         transaction.getVendor(), transaction.getAmount())
         }
        sout("");
        
[need to also add deposits ] maybe can combine these?

        sout("deposits: ");
        sout("Date | Time | Description | Vendor | Amount");

        for (Transaction transaction : transactions) {

        if(transaction.getAmount() > 0) { "%s | %s | %s  | %s | %.2f\n",
         transaction.getDate(), transaction.getTime(), transaction.getDescription(),
         transaction.getVendor(), transaction.getAmount())

        }
        sout("");

[need ability to view reports ]

### submenu for reports

    private static void reportMenu (Scanner scanner , ArrayList<Transaction>
    transactions) {
    sout ("Which report do you need?");
    sout("\t1 for month to date");
    sout ("\t2 for previous month month");
    sout("\t3 for year to date");
    sout("\t4 previous year");
    sout("\t5 search by vendor");
    sout("\t6 go back);

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
        sout("nope try again");
    }

[need to have methods for each case]

[monthToDate] 
    
    private static void monthToDateReport(ArrayList<Transaction> 
    transactions) {
    sout (" enter the start date of the report (
    YYYY-MM-DD):");
    String startDate = scanner.nextLine();
    sout(" enter the end date of the report (YYYY-MM-DD):");
    String endDate = scanner.nextLine();

    List<Transaction> monthToDateTransactions = 
    getTransactionsBetweenDates(
    transactions, startDate, endDate);
    for (Transaction transaction : monthToDateTransactions) {
        System.out.println(transaction);
    }
[previousMonth]

    private static void previousMonthReport(ArrayList<Transaction> transactions) {
    System.out.println("Please enter the start date of the report (YYYY-MM-DD):");
    String startDate = scanner.nextLine();
    System.out.println("Please enter the end date of the report (YYYY-MM-DD):");
    String endDate = scanner.nextLine();

    List<Transaction> previousMonthTransactions = getTransactionsBetweenDates(transactions, startDate, endDate);
    for (Transaction transaction : previousMonthTransactions) {
        System.out.println(transaction);
    }}

[ytd]

    private static void yearToDateReport(ArrayList<Transaction> transactions) {
    System.out.println("Please enter the start date of the report (YYYY-MM-DD):");
    String startDate = scanner.nextLine();
    System.out.println("Please enter the end date of the report (YYYY-MM-DD):");
    String endDate = scanner.nextLine();

    List<Transaction> yearToDateTransactions = getTransactionsBetweenDates(transactions, startDate, endDate);
    for (Transaction transaction : yearToDateTransactions) {
        System.out.println(transaction);
    }}

[previousYear]

        private static void previousYearReport(ArrayList<Transaction> transactions) {
    System.out.println("Please enter the start date of the report (YYYY-MM-DD):");
    String startDate = scanner.nextLine();
    System.out.println("Please enter the end date of the report (YYYY-MM-DD):");
    String endDate = scanner.nextLine();

    List<Transaction> previousYearTransactions = getTransactionsBetweenDates(transactions, startDate, endDate);
    for (Transaction transaction : previousYearTransactions) {
        System.out.println(transaction);
    }}
[vendor]
    
    private static void vendorReport(ArrayList<Transaction> transactions) {
    System.out.println("Please enter the name of the vendor:");
    String vendorName = scanner.nextLine();
    
    List<Transaction> vendorTransactions = getTransactionsByVendor(
    transactions, vendorName);
    for (Transaction transaction : vendorTransactions) {
        System.out.println(transaction);
    }}






### Transaction class 

    public class Transaction {
        private String date;
        private String time;
        private String description;
        private String vendor;
        private double amount;

    public Transaction(String date, String time, String description,
                       String vendor, double amount)
    {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }


}


    



    

