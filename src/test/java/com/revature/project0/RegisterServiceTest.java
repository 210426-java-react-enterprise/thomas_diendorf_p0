package com.revature.project0;

import com.revature.project0.daos.UserDAO;
import com.revature.project0.services.RegisterService;
import org.junit.After;
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

    @Test
    public void testInputUsername(){
        //arrange

        //act

        //assert

    }

}
