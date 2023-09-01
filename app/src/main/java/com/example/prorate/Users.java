package com.example.prorate;

import java.util.ArrayList;

public class Users {




    private String username;
    private String password;
    private String firstName;



    private String lastName;
    private Integer an;

    private Integer status;
    private int id;

    private String facultate;

    private String specializare;


    public Users(String username, String password, String firstName, String lastName, Integer an, Integer status, int id, String facultate, String specializare, ArrayList<String> materii) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.an = an;
        this.status = status;
        this.id = id;
        this.facultate = facultate;
        this.specializare = specializare;
        this.materii = materii;
    }

    private ArrayList<String> materii;

    public Users() {

    }


    public String getFacultate() {
        return facultate;
    }

    public void setFacultate(String facultate) {
        this.facultate = facultate;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public ArrayList<String> getMaterii() {
        return materii;
    }

    public void setMaterii(ArrayList<String> materii) {
        this.materii = materii;
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

    public Integer getAn() {
        return an;
    }

    public void setAn(Integer an) {
        this.an = an;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor

}