package com.revature.project0.models;

/*
aka Account Owner
Can exist independent of an Account, I guess.
"AppUser" better name than "AccountUser" because
technically using an app to login/create an account
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
    //private String birthDate;//TODO: implement this
    //private String zipcode;//TODO: don't implement unless setting up separate SQL table
    private String accountID;//foreign key for SQL table
    //would this need a password?  Probably not if the account itself requires the password

    public AppUser() {
        super();
    }

    //TODO: make check to see if username is unique
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*
    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }
     */

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

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
        //sb.append(", age=").append(age);
        sb.append(", address=").append(address);
        sb.append(", city=").append(city);
        sb.append(", state=").append(state);
        //sb.append(", zipcode=").append(zipcode);
        //sb.append(", phone=").append(phone);
        sb.append('}');
        return sb.toString();
    }
}
