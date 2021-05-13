package com.revature.project0.models;

/*
aka Account Owner
Can exist independent of an Account, I guess.
"AppUser" better name than "AccountUser" because
technically using an app to login/create an account
 */

/**
 * Information about the current app user, stored for the duration user is logged in.
 */
public class AppUser {
    private String username;//aka primary key for SQL table
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String city;
    private String state;
    //private String phone;
    //private String birthDate;
    //private int ssn;
    //private String zipcode;//don't implement unless setting up separate SQL table
    private String accountID;

    public AppUser() {
        super();
    }


    //users normally don't start with an accoundID, particularly if registering for first time
    public AppUser(String username, String password, String firstName, String lastName, String email,
                   String address, String city, String state/*, String zipcode,
                   String phone, int age*/ ){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        //this.age = age;
        this.address = address;
        this.city = city;
        this.state = state;
        //this.phone = phone;
        //this.zipcode = zipcode;

    }


    //to use when logging in instead of registering
    public AppUser(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * Gets username of current app user.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username of current app user.
     * @param username String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return user's password as String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set's user's password.
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return first name of user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of user.
     * @param firstName String
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return last name of user as String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name of user.
     * @param lastName String
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return user email as String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    /**
     *
     * @return street address of user as String
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets user's street address.
     * @param address String
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return city of user as String
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city of user.
     * @param city String
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return state of user as 2 capitalized letters in String format
     */
    public String getState() {
        return state;
    }

    /**
     * Sets user's state as 2 capitalized letters.
     * @param state String
     */
    public void setState(String state) {
        this.state = state;
    }

    /*
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    */

    //for when user's information needs to be conveniently outputted as a string
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppUser{");
        sb.append("username='").append(username).append('\'');
        sb.append(", account_id='").append(accountID).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", address=").append(address);
        sb.append(", city=").append(city);
        sb.append(", state=").append(state);
        //sb.append(", zipcode=").append(zipcode);
        //sb.append(", phone=").append(phone);
        sb.append('}');
        return sb.toString();
    }
}
