package com.revature.project0.serviceTests;

import com.revature.project0.daos.UserDAO;
import com.revature.project0.services.RegisterService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RegisterServiceTest {

    RegisterService registerService;
    BufferedReader consoleReader;
    UserDAO userDAO;

    @Before
    public void setUpTest() {
        consoleReader = new BufferedReader(new InputStreamReader(System.in));
        userDAO = new UserDAO();
        registerService = new RegisterService(consoleReader, userDAO);
    }

    @After
    public void tearDownTest(){
        consoleReader = null;
        userDAO = null;
        registerService = null;
    }

    //It's very important the test usernames don't already exist in the database!!!!
    @Test
    public void testInputUsername(){
        //arrange
        String test1 = "Tim";
        String test2 = "Ty";
        String test3 = "timothy";
        String test4 = "";
        String test5 = "$^percal^*(@#$ragi672expialadocious";
        String test6 = "e";

        //act
        String result1 = registerService.serviceUsername(test1);
        String result2 = registerService.serviceUsername(test2);
        String result3 = registerService.serviceUsername(test3);
        String result4 = registerService.serviceUsername(test4);
        String result5 = registerService.serviceUsername(test5);
        String result6 = registerService.serviceUsername(test6);

        //assert
        Assert.assertEquals(test1, result1);
        Assert.assertNull(result2);
        Assert.assertEquals(test3, result3);
        Assert.assertNull(result4);
        Assert.assertNull(result5);
        Assert.assertEquals(test6, result6);

    }

    @Test
    public void testPassword(){
        //arrange
        String test1 = "";
        String test2 = "*#dskn";
        String test3 = "p@ssword";
        String test4 = "passwords";
        String test5 = "(_$#%($%#(^^^^#";
        String test6 = "passit0n35208$^#@$#dfmanonpppppp";
        String test7 = "e";

        //act
        String result1 = registerService.servicePassword(test1);
        String result2 = registerService.servicePassword(test2);
        String result3 = registerService.servicePassword(test3);
        String result4 = registerService.servicePassword(test4);
        String result5 = registerService.servicePassword(test5);
        String result6 = registerService.servicePassword(test6);
        String result7 = registerService.servicePassword(test7);

        //test
        Assert.assertNull(result1);
        Assert.assertNull(result2);
        Assert.assertEquals(test3, result3);
        Assert.assertEquals(test4, result4);
        Assert.assertEquals(test5, result5);
        Assert.assertNull(result6);
        Assert.assertEquals(test7, result7);

    }

    @Test
    public void testFirstName(){
        //arrange
        String test1 = "Tim";
        String test2 = "Ty";
        String test3 = "timothy";
        String test4 = "";
        String test5 = "$^percal^*(@#$ragi672expialadocious";
        String test6 = "e";
        String test7 = "m#*%Dees";
        String test8 = "Supercalifragilisticexpialadicious";

        //act
        String result1 = registerService.serviceFirstName(test1);
        String result2 = registerService.serviceFirstName(test2);
        String result3 = registerService.serviceFirstName(test3);
        String result4 = registerService.serviceFirstName(test4);
        String result5 = registerService.serviceFirstName(test5);
        String result6 = registerService.serviceFirstName(test6);
        String result7 = registerService.serviceFirstName(test7);
        String result8 = registerService.serviceFirstName(test8);

        //assert
        Assert.assertEquals(test1, result1);
        Assert.assertNull(result2);
        Assert.assertEquals(test3, result3);
        Assert.assertNull(result4);
        Assert.assertNull(result5);
        Assert.assertEquals(test6, result6);
        Assert.assertNull(result7);
        Assert.assertNull(result8);
    }

    @Test
    public void testLastName(){
        //arrange
        String test1 = "Tim";
        String test2 = "Ty";
        String test3 = "timothy";
        String test4 = "";
        String test5 = "$^percal^*(@#$ragi672expialadocious";
        String test6 = "e";
        String test7 = "m#*%Dees";
        String test8 = "Supercalifragilisticexpialadicious";

        //act
        String result1 = registerService.serviceLastName(test1);
        String result2 = registerService.serviceLastName(test2);
        String result3 = registerService.serviceLastName(test3);
        String result4 = registerService.serviceLastName(test4);
        String result5 = registerService.serviceLastName(test5);
        String result6 = registerService.serviceLastName(test6);
        String result7 = registerService.serviceLastName(test7);
        String result8 = registerService.serviceLastName(test8);

        //assert
        Assert.assertEquals(test1, result1);
        Assert.assertNull(result2);
        Assert.assertEquals(test3, result3);
        Assert.assertNull(result4);
        Assert.assertNull(result5);
        Assert.assertEquals(test6, result6);
        Assert.assertNull(result7);
        Assert.assertNull(result8);
    }

    //It's very important the test emails don't already exist in the database!!!!
    @Test
    public void testEmail(){
        //arrange
        String test1 = "doug@revature.net";
        String test2 = "@revature.net";
        String test3 = "t@revature.com";
        String test4 = "Tike@revature";
        String test5 = "e";

        //act
        String result1 = registerService.serviceEmail(test1);
        String result2 = registerService.serviceEmail(test2);
        String result3 = registerService.serviceEmail(test3);
        String result4 = registerService.serviceEmail(test4);
        String result5 = registerService.serviceEmail(test5);

        //assert
        Assert.assertEquals(test1, result1);
        Assert.assertNull(result2);
        Assert.assertEquals(test3, result3);
        Assert.assertNull(result4);
        Assert.assertEquals(test5, result5);

    }

    @Test
    public void testAddress(){
        //arrange
        String test1 = "09876 Candy Lane";
        String test2 = "0987654 Candy Lane";
        String test3 = "Candy Lane";
        String test4 = "12 Candy Lane";
        String test5 = "1234 Candy Lane and Candy Canes and Candy Corn";
        String test6 = "e";

        //act
        String result1 = registerService.serviceAddress(test1);
        String result2 = registerService.serviceAddress(test2);
        String result3 = registerService.serviceAddress(test3);
        String result4 = registerService.serviceAddress(test4);
        String result5 = registerService.serviceAddress(test5);
        String result6 = registerService.serviceAddress(test6);

        //assert
        Assert.assertEquals(test1, result1);
        Assert.assertNull(result2);
        Assert.assertNull(result3);
        Assert.assertNull(result4);
        Assert.assertNull(result5);
        Assert.assertEquals(test6, result6);
    }

    @Test
    public void testCity(){
        //arrange
        String test1 = "City";
        String test2 = "";
        String test3 = "Cy";
        String test4 = "common";
        String test5 = "Napalm Landscape of Tacos and Hot Sauce";
        String test6 = "e";
        String test7 = "Abu ^dabi";

        //act
        String result1 = registerService.serviceCity(test1);
        String result2 = registerService.serviceCity(test2);
        String result3 = registerService.serviceCity(test3);
        String result4 = registerService.serviceCity(test4);
        String result5 = registerService.serviceCity(test5);
        String result6 = registerService.serviceCity(test6);
        String result7 = registerService.serviceCity(test7);

        //assert
        Assert.assertEquals(test1, result1);
        Assert.assertNull(result2);
        Assert.assertNull(result3);
        Assert.assertEquals(test4, result4);
        Assert.assertNull(result5);
        Assert.assertEquals(test6, result6);
        Assert.assertNull(result7);
    }

    @Test
    public void testState(){
        //arrange
        String test1 = "CA";
        String test2 = "C";
        String test3 = "";
        String test4 = "NYC";
        String test5 = "e";

        //act
        String result1 = registerService.serviceState(test1);
        String result2 = registerService.serviceState(test2);
        String result3 = registerService.serviceState(test3);
        String result4 = registerService.serviceState(test4);
        String result5 = registerService.serviceState(test5);

        //assert
        Assert.assertEquals(test1, result1);
        Assert.assertNull(result2);
        Assert.assertNull(result3);
        Assert.assertNull(result4);
        Assert.assertEquals(test5, result5);
    }

}
