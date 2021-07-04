package com.cmpe202.demo.model;

public class ListingSearch {
    private String zip;
    private String address;
    private Integer priceLow;
    private Integer priceHigh;
    private Integer sqftLow;
    private Integer sqftHigh;
    private Integer bedroom;
    private Integer bathroom;
    private String flooring;
    private String listingType;
    private String homeType;
    private Integer year;
    private String parking;

    public ListingSearch() {
    }

    public ListingSearch(String zip, String address, Integer priceLow, Integer priceHigh, Integer sqftLow, Integer sqftHigh, Integer bedroom, Integer bathroom, String flooring, String listingType, String homeType, Integer year, String parking) {
        this.zip = zip;
        this.address = address;
        this.priceLow = priceLow;
        this.priceHigh = priceHigh;
        this.sqftLow = sqftLow;
        this.sqftHigh = sqftHigh;
        this.bedroom = bedroom;
        this.bathroom = bathroom;
        this.flooring = flooring;
        this.listingType = listingType;
        this.homeType = homeType;
        this.year = year;
        this.parking = parking;
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

    public Integer getPriceLow() {
        return priceLow;
    }

    public void setPriceLow(Integer priceLow) {
        this.priceLow = priceLow;
    }

    public Integer getPriceHigh() {
        return priceHigh;
    }

    public void setPriceHigh(Integer priceHigh) {
        this.priceHigh = priceHigh;
    }

    public Integer getSqftLow() {
        return sqftLow;
    }

    public void setSqftLow(Integer sqftLow) {
        this.sqftLow = sqftLow;
    }

    public Integer getSqftHigh() {
        return sqftHigh;
    }

    public void setSqftHigh(Integer sqftHigh) {
        this.sqftHigh = sqftHigh;
    }

    public Integer getBedroom() {
        return bedroom;
    }

    public void setBedroom(Integer bedroom) {
        this.bedroom = bedroom;
    }

    public Integer getBathroom() {
        return bathroom;
    }

    public void setBathroom(Integer bathroom) {
        this.bathroom = bathroom;
    }

    public String getFlooring() {
        return flooring;
    }

    public void setFlooring(String flooring) {
        this.flooring = flooring;
    }

    public String getListingType() {
        return listingType;
    }

    public void setListingType(String listingType) {
        this.listingType = listingType;
    }

    public String getHomeType() {
        return homeType;
    }

    public void setHomeType(String homeType) {
        this.homeType = homeType;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }
}
