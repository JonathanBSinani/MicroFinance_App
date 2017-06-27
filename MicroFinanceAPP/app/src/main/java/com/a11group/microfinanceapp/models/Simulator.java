package com.a11group.microfinanceapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jonathan on 25/06/2017.
 */

public class Simulator {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("birthdate")
    @Expose
    private String birthdate;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("retired")
    @Expose
    private int retired;
    @SerializedName("year")
    @Expose
    private int year;
    @SerializedName("money")
    @Expose
    private double money;
    @SerializedName("accumulatedValue")
    @Expose
    private String accumulatedValue;
    @SerializedName("monthlyContribution")
    @Expose
    private String monthlyContribution;
    @SerializedName("created_at")
    @Expose
    private String created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getRetired() {
        return retired;
    }

    public void setRetired(int retired) {
        this.retired = retired;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getAccumulatedValue() {
        return accumulatedValue;
    }

    public void setAccumulatedValue(String accumulatedValue) {
        this.accumulatedValue = accumulatedValue;
    }

    public String getMonthlyContribution() {
        return monthlyContribution;
    }

    public void setMonthlyContribution(String monthlyContribution) {
        this.monthlyContribution = monthlyContribution;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
