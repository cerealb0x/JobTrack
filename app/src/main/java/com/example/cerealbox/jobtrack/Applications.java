package com.example.cerealbox.jobtrack;

/**
 * Created by cerealbox on 2015-08-14.
 */
public class Applications {

    private String company;
    private String position;
    private int month;
    private int day;
    private int year;
    private int interviewStatus;
    private int offerStatus;
    private String aid;

    /*Getter method for position variable*/
    public String getPosition() {
        return position;
    }

    /*Setter method for position variable*/
    public void setPosition(String position) {
        this.position = position;
    }

    /*Getter method for company variable*/
    public String getCompany() {
        return company;
    }

    /*Setter method for company variable*/
    public void setCompany(String company) {
        this.company = company;
    }

    /*Getter method for company variable*/
    public int getMonth() {
        return month;
    }

    /*Setter method for company variable*/
    public void setMonth(int month) {
        this.month = month;
    }

    /*Getter method for company variable*/
    public int getDay() {
        return day;
    }

    /*Setter method for company variable*/
    public void setDay(int day) {
        this.day = day;
    }

    /*Getter method for company variable*/
    public int getYear() {
        return year;
    }

    /*Setter method for company variable*/
    public void setYear(int year) {
        this.year = year;
    }

    /*Getter method for company variable*/
    public String getAid() {
        return aid;
    }

    /*Setter method for company variable*/
    public void setAid(String aid) {
        this.aid = aid;
    }


    public int getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(int interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public int getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(int offerStatus) {
        this.offerStatus = offerStatus;
    }

    /*Gets the date value in the following format: Month/Day/Year*/
    public String getDateAsString(){
        return String.valueOf(month)+ "/" + String.valueOf(day) + "/" + String.valueOf(year);
    }

    /*This class' toString method, which decides how its info is presented when toString is called*/
    @Override
    public String toString(){
        return "Company: " + company + "    Position: " + position + "   Applied: " + month + "/" + day + "/" + year;
    }








}
