package com.revature.project0.doaTests;

import com.revature.project0.daos.AccountDAO;
import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.AppAccount;
import com.revature.project0.models.AppUser;
import com.revature.project0.screens.AccountScreen;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class AccountDOATest {

    AccountDAO accountDAO;
    AppUser appUser;
    UserDAO userDAO;
    AccountScreen accountScreen;
    AppAccount appAccount;

    @Before
    public void setUpTest(){
        accountDAO = new AccountDAO();
        appUser = new AppUser("testcase", "p@$sw0Rd", "Test", "Case", "testcase@revature.net",
                "12345 Testcase Rd.", "Test City", "TC");

        accountScreen = mock(AccountScreen.class);

        userDAO = new UserDAO();

        userDAO.save(appUser);

        accountDAO.createAccount(appUser, "checking");
        appAccount = accountDAO.findAccountByUsername(appUser.getUsername());


    }

    @After
    public void tearDownTest(){
        if(appAccount != null) {
            if (userDAO.findUserByUsername(appUser.getUsername())) {
                double balance = appAccount.getBalance();
                appAccount.withdraw(balance);
                userDAO.removeUserWithAccount(appUser.getUsername(), accountDAO);
            }
        } else if(userDAO.findUserByUsername(appUser.getUsername())){
            userDAO.removeUserWithoutAccount(appUser.getUsername());
        }

        accountDAO = null;
        appUser = null;
        userDAO = null;
        appAccount = null;

    }

    @Test
    public void testInputStringCurrencyToDouble(){
        //arrange
        String test1 = "$0.00";
        String test2 = "0.00";
        String test3 = "$5.00";
        String test4 = "$0.50";
        String test5 = "$0.05";
        String test6 = "$50.05";
        String test7 = "$5020.05";
        String test8 = "$5,020.05";
        String test9 = "$56,794,020.05";
        String test10 = "56,794,020.05";

        //act
        double result1 = accountDAO.stringCurrencyToDouble(test1);
        double result2 = accountDAO.stringCurrencyToDouble(test2);
        double result3 = accountDAO.stringCurrencyToDouble(test3);
        double result4 = accountDAO.stringCurrencyToDouble(test4);
        double result5 = accountDAO.stringCurrencyToDouble(test5);
        double result6 = accountDAO.stringCurrencyToDouble(test6);
        double result7 = accountDAO.stringCurrencyToDouble(test7);
        double result8 = accountDAO.stringCurrencyToDouble(test8);
        double result9 = accountDAO.stringCurrencyToDouble(test9);
        double result10 = accountDAO.stringCurrencyToDouble(test10);

        //assert
        Assert.assertEquals(0, result1, 0.01);
        Assert.assertEquals(0, result2, 0.01);
        Assert.assertEquals(5, result3, 0.01);
        Assert.assertEquals(0.5, result4, 0.01);
        Assert.assertEquals(0.05, result5, 0.01);
        Assert.assertEquals(50.05, result6, 0.01);
        Assert.assertEquals(5020.05, result7, 0.01);
        Assert.assertEquals(5020.05, result8, 0.01);
        Assert.assertEquals(56794020.05, result9, 0.01);
        Assert.assertEquals(56794020.05, result10, 0.01);
    }


    @Test
    public void testDoubleToStringCurrency(){
        //arrange
        double test1 = 0.00;
        double test2 = 0;
        double test3 = 5.00;
        double test4 = 0.50;
        double test5 = 0.05;
        double test6 = 50.05;
        double test7 = 5020.05;
        double test8 = 56794020.05;

        //act
        String result1 = accountDAO.doubleToStringCurrency(test1);
        String result2 = accountDAO.doubleToStringCurrency(test2);
        String result3 = accountDAO.doubleToStringCurrency(test3);
        String result4 = accountDAO.doubleToStringCurrency(test4);
        String result5 = accountDAO.doubleToStringCurrency(test5);
        String result6 = accountDAO.doubleToStringCurrency(test6);
        String result7 = accountDAO.doubleToStringCurrency(test7);
        String result8 = accountDAO.doubleToStringCurrency(test8);

        //assert
        Assert.assertEquals("$0.00", result1);
        Assert.assertEquals("$0.00", result2);
        Assert.assertEquals("$5.00", result3);
        Assert.assertEquals("$0.50", result4);
        Assert.assertEquals("$0.05", result5);
        Assert.assertEquals("$50.05", result6);
        Assert.assertEquals("$5,020.05", result7);
        Assert.assertEquals("$56,794,020.05", result8);
    }



    @Test
    public void testMakeDepositAndWithdrawal(){

        appAccount = accountDAO.makeDeposit(appUser.getUsername(), 0);
        Assert.assertEquals(0, appAccount.getBalance(), 0.01);

        appAccount = accountDAO.makeDeposit(appUser.getUsername(), -50);
        Assert.assertEquals(0, appAccount.getBalance(), 0.01);

        appAccount = accountDAO.makeDeposit(appUser.getUsername(), 50.002);
        Assert.assertEquals(50.00, appAccount.getBalance(), 0.01);

        appAccount = accountDAO.makeDeposit(appUser.getUsername(), 52.99);
        Assert.assertEquals(102.99, appAccount.getBalance(), 0.01);

        appAccount = accountDAO.makeWithdrawal(appUser.getUsername(), 102.99);
        Assert.assertEquals(0, appAccount.getBalance(), 0.01);

        appAccount = accountDAO.makeWithdrawal(appUser.getUsername(), 102.99);
        Assert.assertEquals(0, appAccount.getBalance(), 0.01);

        appAccount = accountDAO.makeDeposit(appUser.getUsername(), 0.01);
        Assert.assertEquals(0.01, appAccount.getBalance(), 0.01);

        appAccount = accountDAO.makeWithdrawal(appUser.getUsername(), 0.02);
        Assert.assertEquals(0.01, appAccount.getBalance(), 0.01);

        appAccount = accountDAO.makeWithdrawal(appUser.getUsername(), 0.01);
        Assert.assertEquals(0, appAccount.getBalance(), 0.01);
    }





}
