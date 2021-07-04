package com.cmpe202.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Listing {
    @Id
    private String uuid;

    //not nullable
    private String publisher;
    private int price;
    private int sqft;
    private int numberOfBedroom;
    private int numberOfBathroom;
    private String flooring;
    private String type;
    private String zip;
    private String address;
    private Integer year;
    private String parking;
    private String listingType;
    //rent specific (not nullable)
    private String leaseTerm;
    private String availableDate;
    private int deposit;

    private String status = "open";
    private String other;
    private String openHouse;


    public Listing() {
    }

    public Listing(String uuid, String publisher, String openHouse, String status, int price, int sqft, int numberOfBedroom, int numberOfBathroom, String leaseTerm, String availableDate, int deposit, String flooring, String type, String zip, String address, Integer year, String other, String parking, String listingType) {
        this.uuid = uuid;
        this.publisher = publisher;
        this.openHouse = openHouse;
        this.status = status;
        this.price = price;
        this.sqft = sqft;
        this.numberOfBedroom = numberOfBedroom;
        this.numberOfBathroom = numberOfBathroom;
        this.leaseTerm = leaseTerm;
        this.availableDate = availableDate;
        this.deposit = deposit;
        this.flooring = flooring;
        this.type = type;
        this.zip = zip;
        this.address = address;
        this.year = year;
        this.other = other;
        this.parking = parking;
        this.listingType = listingType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getOpenHouse() {
        return openHouse;
    }

    public void setOpenHouse(String openHouse) {
        this.openHouse = openHouse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSqft() {
        return sqft;
    }

    public void setSqft(int sqft) {
        this.sqft = sqft;
    }

    public int getNumberOfBedroom() {
        return numberOfBedroom;
    }

    public void setNumberOfBedroom(int numberOfBedroom) {
        this.numberOfBedroom = numberOfBedroom;
    }

    public int getNumberOfBathroom() {
        return numberOfBathroom;
    }

    public void setNumberOfBathroom(int numberOfBathroom) {
        this.numberOfBathroom = numberOfBathroom;
    }

    public String getLeaseTerm() {
        return leaseTerm;
    }

    public void setLeaseTerm(String leaseTerm) {
        this.leaseTerm = leaseTerm;
    }

    public String getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(String availableDate) {
        this.availableDate = availableDate;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getFlooring() {
        return flooring;
    }

    public void setFlooring(String flooring) {
        this.flooring = flooring;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getListingType() {
        return listingType;
    }

    public void setListingType(String listingType) {
        this.listingType = listingType;
    }
}
