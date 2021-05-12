package com.revature.project0.doaTests;

import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.AppUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserDAOTest {

    UserDAO userDAO;
    AppUser appUser;

    @Before
    public void setUpTest(){
        userDAO = new UserDAO();

        appUser = new AppUser("testcase", "p@$sw0Rd", "Test", "Case", "testcase@revature.net",
                "12345 Testcase Rd.", "Test City", "TC");
    }

    @After
    public void tearDownTest(){
        if(userDAO.findUserByUsername(appUser.getUsername())){
            userDAO.removeUserWithoutAccount(appUser.getUsername());
        }

        userDAO = null;
        appUser = null;
    }


    @Test
    public void testSaveUser(){
        userDAO.save(appUser);
        Assert.assertTrue(userDAO.findUserByUsername(appUser.getUsername()));

        AppUser appUserTemp = userDAO.findUserByUsernameAndPassword(appUser.getUsername(), appUser.getPassword());

        Assert.assertEquals(appUser.getUsername(), appUserTemp.getUsername());
        Assert.assertEquals(appUser.getPassword(), appUserTemp.getPassword());
        Assert.assertEquals(appUser.getEmail(), appUserTemp.getEmail());
        Assert.assertEquals(appUser.getFirstName(), appUserTemp.getFirstName());
        Assert.assertEquals(appUser.getLastName(), appUserTemp.getLastName());
        Assert.assertEquals(appUser.getAddress(), appUserTemp.getAddress());
        Assert.assertEquals(appUser.getCity(), appUserTemp.getCity());
        Assert.assertEquals(appUser.getState(), appUserTemp.getState());

    }


    //TODO: make this work!
    //if email is taken, returns true!
    @Test
    public void testFindByEmail(){
        String test1 = appUser.getEmail();
        String test2 = "bs@revature.net";

        Assert.assertFalse(userDAO.findEmailByEmail(test1));
        Assert.assertTrue(userDAO.findEmailByEmail(test2));
    }


    @Test
    public void testDeleteUserWithoutAccount(){
        //assemble
        if(!userDAO.findUserByUsername(appUser.getUsername())){
            userDAO.save(appUser);
        }

        //act
        userDAO.removeUserWithoutAccount(appUser.getUsername());

        //assert
        Assert.assertFalse(userDAO.findUserByUsername(appUser.getUsername()));
    }



}
