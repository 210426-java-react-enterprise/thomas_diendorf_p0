package com.revature.project0.services;

//import java.io.BufferedReader;

public class AccountService {

    //private BufferedReader consoleReader;

    public AccountService(){
        super();
    }

    /*
    public String inputDoubleCurrencyToString(double dValue){

        String sValue = "$";

        try {
            sValue += Double.parseDouble(consoleReader.readLine());
        } catch (Exception e){
            e.printStackTrace();
        }

        return sValue;

    }
    */

    public double inputStringCurrencyToDouble(String sValue){

        double dValue = 0;
        String noCommas = "";

        noCommas = sValue.replaceAll(",", "");//in case large number with commas is passed

        dValue = Double.parseDouble(noCommas);

        return dValue;

    }

}
