package com.revature.project0.doaTests;

import com.revature.project0.daos.UserDAO;
import org.junit.Before;

public class UserDAOTest {

    UserDAO userDAO;

    @Before
    public void setUpTest(){
        userDAO = new UserDAO();
    }

}
