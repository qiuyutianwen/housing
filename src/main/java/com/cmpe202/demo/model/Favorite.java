package com.cmpe202.demo.model;

import com.cmpe202.demo.idclass.ApplicationId;
import com.cmpe202.demo.idclass.FavoriteId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(FavoriteId.class)
public class Favorite {
    @Id
    private String user;
    @Id
    private String listing;

    public Favorite() {
    }

    public Favorite(String user, String listing) {
        this.user = user;
        this.listing = listing;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getListing() {
        return listing;
    }

    public void setListing(String listing) {
        this.listing = listing;
    }
}
