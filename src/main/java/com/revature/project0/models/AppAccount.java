package com.revature.project0.models;

/*
Should this class be abstract, or have an
abstract class or interface to inherit from?

Dependent on AccountUser to exist
*/

import java.sql.Date;


/**
 * Stores information for current user.  Dependent on AppUser class having been
 * instantiated earlier in the program.
 */
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


    /**
     *
     * @return username String of user who created (and thus owns) the account.
     */
    public String getAccountOwner() {
        return accountOwner;
    }

    /**
     *
     * @param accountOwner String
     */
    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public String getAccountID() {
        return accountID;
    }

    /**
     * Identifier for the account belonging to user.
     * @param accountID String
     */
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAccountType() {
        return accountType;
    }

    /**
     * Either "checking" or "savings"
     * @param accountType String
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Date that account is created.
     * @param dateCreated Date
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Sets USD balance for account.
     * @param balance double
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets the user's account balance.
     * @return USD amount in double format
     */
    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    /**
     * Withdraw an amount from user's account balance
     * @param amount double
     */
    //add constraint to this on sql table that prevents it from falling below 0
    public void withdraw(double amount) {
        this.balance -= amount;
    }
}
