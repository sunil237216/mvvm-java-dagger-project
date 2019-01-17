package com.example.test.mvvmsampleapp.service.model;

/**
 * Created by sunil.jadhav on 11/12/2018.
 */

public class LoginResponse {


    /**
     * Name : lucky
     * EmailId : admin
     * Password : admin
     * MobileNumber : 981281821
     * UserId : 1
     */

    private String Name;
    private String EmailId;
    private String Password;
    private int MobileNumber;
    private int UserId;
    private String Token;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String EmailId) {
        this.EmailId = EmailId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(int MobileNumber) {
        this.MobileNumber = MobileNumber;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }
}