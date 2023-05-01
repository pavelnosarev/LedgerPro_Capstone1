# LedgerPro_Capstone1


*requirements*
-
*display a menu of options to user : addDeposit, payment, viewLedger
, exit

*user will be able to make selection with those options

[addDeposit:  add deposit using date, time, description, vendor, amount]

[payment: add payment using date, time, description, vendor, amount]

[ledger: show ledger screen]

[exit]

*ledger needs to show everything in inventory.csv file (newest first), 
they can filter by [All, Deposits, Payments]

*reports will show reports [monthToMonth, previousMonth, yearToDate, 
previousYear]

*will be able to search by [vendor] name, go back and forth from 
[reportPage] and [homePage]

# pseudocode

* import java.io.File;
* import java.util.Scanner;
* import java.io.FileNotFoundException;
* import java.IOException;
* import java.util.ArrayList;

*public class LedgerPro 

*  [private static final String TRANSACTION_FILE =
   "transactions.csv"; [to read the csv file] ]

  * create a main class inside our LedgerPro  : [public static void 
  * main(String[] args) throws FileNotFoundException{      ] 
  
  * create Scanner scanner = new Scanner (System.in); [takes in user input];
  * need an ArrayList to store the items [ArrayList<Transaction> 
   transactions = new ArrayList <>() ;]
  * need to use a do {} while loop to show the menu for my users 
  
    [maybe do a while(true)]?
    ## menu 
  [ while (true){  
            sout ("here are our options: ");
            sout("\tD add deposit");
            sout("\tP make payment");
            sout("\tL view ledger");
            sout("\tQ quit app");
]
  *    need var for user input: [String input = scanner.nextLine();]
  * switch statement to allow different paths depending on user input
  
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
                    sout("nope that's not an option try again");

                }
            }
        }
  * need methods for addDeposit, payment, viewLedger, 
  * and saving transactions to my 'transactions.csv'

    private static void addDeposit(Scanner scanner, ArrayList<
Transaction> transactions){
    sout ("enter date for deposit (yyyy-mm-dd):  ");
    String date = scanner.nextLine();
[do the same for time(hh:mm:ss), description, vendor, amount]
[also need a .add]? need to lookup how to? or ask greg?




}

    

