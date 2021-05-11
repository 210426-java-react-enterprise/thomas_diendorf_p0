package com.revature.project0;

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
        String value1 = "0.00";
        String value2 = "1.00";
        String value3 = "1.10";
        String value4 = "1.11";
        String value5 = "0.01";
        String value6 = "0.10";
        String value7 = "1,115,900.10";


        //act
        double result1 = accountService.inputStringCurrencyToDouble(value1);
        double result2 = accountService.inputStringCurrencyToDouble(value2);
        double result3 = accountService.inputStringCurrencyToDouble(value3);
        double result4 = accountService.inputStringCurrencyToDouble(value4);
        double result5 = accountService.inputStringCurrencyToDouble(value5);
        double result6 = accountService.inputStringCurrencyToDouble(value6);
        double result7 = accountService.inputStringCurrencyToDouble(value7);

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
