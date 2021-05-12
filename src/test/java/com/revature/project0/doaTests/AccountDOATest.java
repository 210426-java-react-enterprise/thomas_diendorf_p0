package com.revature.project0.doaTests;

import com.revature.project0.daos.AccountDAO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountDOATest {

    AccountDAO accountDAO;

    @Before
    public void setUpTest(){
        accountDAO = new AccountDAO();
    }

    @After
    public void tearDownTest(){
        accountDAO = null;
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




}
