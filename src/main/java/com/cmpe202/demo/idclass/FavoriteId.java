package com.cmpe202.demo.idclass;

import com.cmpe202.demo.model.Favorite;

import java.io.Serializable;
import java.util.Objects;

public class FavoriteId implements Serializable {
    private String user;
    private  String listing;

    public FavoriteId() {
    }

    public FavoriteId(String user, String listing) {
        this.user = user;
        this.listing = listing;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user,listing);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FavoriteId id = (FavoriteId) obj;
        return id.user.equals(this.user) &&
                id.listing.equals(this.listing);
    }
}
