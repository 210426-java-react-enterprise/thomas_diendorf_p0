package com.revature.project0.util;

/**
 * Stores information to be used for the user who is currently logged in.
 */
public class AppUserInfo {

    private String currentUser;
    private String currentUserPassword;

    public AppUserInfo(){
        super();//obligatory
    }

    /**
     * Returns username of logged in user.
     *
     * @return String
     */
    public String getCurrentUser() {
        return currentUser;
    }


    /**
     * Sets username of logged in user.
     *
     */
    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }


    /**
     * Gets the password of the logged in user.
     *
     * @return String
     */
    public String getCurrentUserPassword() {
        return currentUserPassword;
    }


    /**
     * Sets the password of the logged in user.
     *
     */
    public void setCurrentUserPassword(String currentUserPassword) {
        this.currentUserPassword = currentUserPassword;
    }
}
