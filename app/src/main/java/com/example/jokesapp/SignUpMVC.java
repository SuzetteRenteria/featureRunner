package com.example.jokesapp;

import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

public class SignUpMVC extends ViewModel {
    private String fName;
    private String lName;
    private String uName;
    private String pWord;

    public SignUpMVC(){

    }

    public SignUpMVC(String fname, String lname, String pword, String uname){
        this.fName = fname;
        this.lName = lname;
        this.pWord = pword;
        this.uName = uname;
    }
    public String getuName(){ return uName; }
    public Map<String, Object> newPerson() {
        Map<String, Object> newPerson = new HashMap<>();
        newPerson.put("First Name", fName);
        newPerson.put("Last Name", lName);
        newPerson.put("Password", pWord);
        return newPerson;
    }
}
