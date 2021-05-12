package com.revature.project0.doaTests;

import com.revature.project0.daos.AccountDAO;
import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.AppUser;
import org.junit.*;

public class UserDAOTest {

    UserDAO userDAO;
    //AccountDAO accountDAO;
    AppUser appUser;

    @Before
    public void setUpTest(){
        userDAO = new UserDAO();
        //accountDAO = new AccountDAO();

        appUser = new AppUser("testcase", "p@$sw0Rd", "Test", "Case", "testcase@revature.net",
                "12345 Testcase Rd.", "Test City", "TC");

        userDAO.save(appUser);
        //accountDAO.createAccount(appUser, "checking");
    }

    @After
    public void tearDownTest(){
        if(userDAO.findUserByUsername(appUser.getUsername())){
            userDAO.removeUserWithoutAccount(appUser.getUsername());
        }
        //accountDAO.removeUserAccount(appUser.getUsername());

        userDAO = null;
        appUser = null;
    }


    @Test
    public void testSaveUser(){
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


    //if email is taken, returns true!
    @Test
    public void testFindByEmail(){
        String test1 = appUser.getEmail();//should be taken
        String test2 = "bs@revature.net";//should not be in database

        Assert.assertTrue(userDAO.findEmailByEmail(test1));
        Assert.assertFalse(userDAO.findEmailByEmail(test2));
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

    /*
    @After
    public void testRemoveUserWithAccount(){
        Assert.assertTrue(userDAO.removeUserWithAccount(appUser.getUsername(), accountDAO));
    }
    */


}
