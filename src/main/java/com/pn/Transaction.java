package com.pn;

public class Transaction {
    public static String date;
    public static String time;
    public static double amount;
    public static String description;
//    public String description;
    public static String vendor;
//    public double amount;


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

