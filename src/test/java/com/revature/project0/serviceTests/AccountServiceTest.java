package com.revature.project0.serviceTests;

import com.revature.project0.daos.AccountDAO;
import com.revature.project0.services.AccountService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountServiceTest {

    AccountService accountService;

    @Before
    public void setUpTest() {
        accountService = new AccountService();
    }

    @After
    public void tearDownTest(){ accountService = null; }

    @Test
    public void testStringToDouble(){
        //arrange
        AccountDAO accountDAO = new AccountDAO();

        String value1 = "0.00";
        String value2 = "1.00";
        String value3 = "1.10";
        String value4 = "1.11";
        String value5 = "0.01";
        String value6 = "0.10";
        String value7 = "1,115,900.10";


        //act
        double result1 = accountDAO.stringCurrencyToDouble(value1);
        double result2 = accountDAO.stringCurrencyToDouble(value2);
        double result3 = accountDAO.stringCurrencyToDouble(value3);
        double result4 = accountDAO.stringCurrencyToDouble(value4);
        double result5 = accountDAO.stringCurrencyToDouble(value5);
        double result6 = accountDAO.stringCurrencyToDouble(value6);
        double result7 = accountDAO.stringCurrencyToDouble(value7);

        //assert
        Assert.assertEquals(0.0, result1, 0.01);
        Assert.assertEquals(1.00, result2, 0.01);
        Assert.assertEquals(1.10, result3, 0.01);
        Assert.assertEquals(1.11, result4, 0.01);
        Assert.assertEquals(0.01, result5, 0.01);
        Assert.assertEquals(0.10, result6, 0.01);
        Assert.assertEquals(1115900.1, result7, 0.01);

    }

}
