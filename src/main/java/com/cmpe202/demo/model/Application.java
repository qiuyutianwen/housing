package com.cmpe202.demo.model;

import com.cmpe202.demo.idclass.ApplicationId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(ApplicationId.class)
public class Application {
    @Id
    private String applicant;
    @Id
    private String listingId;

    private String type;
    //rent
    private Integer creditScore;
    private String employer;
    private Integer income;
    //buy
    private Integer offer;
    private boolean approved = false;

    private String address;

    public void setOffer(Integer offer) {
        this.offer = offer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Application() {
    }

    public Application(String applicant, String listingId, String type, Integer creditScore, String employer, Integer income, Integer offer, boolean approved, String address) {
        this.applicant = applicant;
        this.listingId = listingId;
        this.type = type;
        this.creditScore = creditScore;
        this.employer = employer;
        this.income = income;
        this.offer = offer;
        this.approved = approved;
        this.address = address;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }
}
