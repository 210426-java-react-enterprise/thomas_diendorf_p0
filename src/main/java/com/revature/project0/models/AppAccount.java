package com.revature.project0.models;

/*
Should this class be abstract, or have an
abstract class or interface to inherit from?

Dependent on AccountUser to exist
*/

import java.sql.Date;

public class AppAccount {
    private String accountID; //unique for sql table entry
    //private String accountName;
    private String accountOwner;//any username
    //private int accountOwnerID; //unique for sql table entry; foreign key
    private String accountType;//make it at least 5 characters in length
    private Date dateCreated;
    private double balance;//restrict decimal digits to 2

    public AppAccount() {
        super();
    }

    public AppAccount(String accountOwner, String accountType, Date dateCreated, double balance){
        this.accountOwner = accountOwner;
        this.accountType = accountType;
        this.dateCreated = dateCreated;
        this.balance = balance;
    }


    public String getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    //add constraint to this on sql table that prevents it from falling below 0
    public void withdraw(double amount) {
        this.balance -= amount;
    }
}
