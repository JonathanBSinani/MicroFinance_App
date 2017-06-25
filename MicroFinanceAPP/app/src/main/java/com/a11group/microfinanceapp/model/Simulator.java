package com.a11group.microfinanceapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Simulator {

    public static class Create {

        private String birthdate;
        private String gender;
        private Integer retired;
        private Integer year;
        private Double money;

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

        public Integer getRetired() {
            return retired;
        }

        public void setRetired(Integer retired) {
            this.retired = retired;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public Double getMoney() {
            return money;
        }

        public void setMoney(Double money) {
            this.money = money;
        }
    }

    public static class Result {

        private Integer id;

        private String birthdate;
        private String gender;

        private Integer retired;
        private Integer year;

        private Double money;
        private Double accumulatedValue;
        private Double monthlyContribution;

        @SerializedName("created_at")
        private Date created;

        @SerializedName("updated_at")
        private Date updated;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
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

        public Integer getRetired() {
            return retired;
        }

        public void setRetired(Integer retired) {
            this.retired = retired;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public Double getMoney() {
            return money;
        }

        public void setMoney(Double money) {
            this.money = money;
        }

        public Double getAccumulatedValue() {
            return accumulatedValue;
        }

        public void setAccumulatedValue(Double accumulatedValue) {
            this.accumulatedValue = accumulatedValue;
        }

        public Double getMonthlyContribution() {
            return monthlyContribution;
        }

        public void setMonthlyContribution(Double monthlyContribution) {
            this.monthlyContribution = monthlyContribution;
        }

        public Date getCreated() {
            return created;
        }

        public void setCreated(Date created) {
            this.created = created;
        }

        public Date getUpdated() {
            return updated;
        }

        public void setUpdated(Date updated) {
            this.updated = updated;
        }
    }
}
