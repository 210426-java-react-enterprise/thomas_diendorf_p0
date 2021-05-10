package com.revature.project0.util;

public class AppUserInfo {

    private String currentUser;
    private String currentUserPassword;

    public AppUserInfo(){
        super();//obligatory
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentUserPassword() {
        return currentUserPassword;
    }

    public void setCurrentUserPassword(String currentUserPassword) {
        this.currentUserPassword = currentUserPassword;
    }
}
