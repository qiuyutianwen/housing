package com.cmpe202.demo.Dao;

import com.cmpe202.demo.DataSource.ListingDataSource;
import com.cmpe202.demo.model.Listing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ListingDao {
    @Autowired
    private ListingDataSource listingDataSource;

    public List<Listing> getALl() {
        return StreamSupport.stream(listingDataSource.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public Listing getById(String id) {
        return listingDataSource.findById(id).orElse(null);
    }

    public String insertListing(Listing listing) {
        listing.setUuid(UUID.randomUUID().toString());
        listingDataSource.save(listing);
        return listing.getUuid();
    }

    public Boolean updateListing(Listing listing) {
        if (listingDataSource.existsById(listing.getUuid())){
            listingDataSource.deleteById(listing.getUuid());
            listingDataSource.save(listing);
            return true;
        }
        return false;
    }

    public Boolean deleteListingById(String id) {
        if (listingDataSource.existsById(id)){
            listingDataSource.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Listing> getByType(String type) {
        return listingDataSource.findByListingType(type);
    }

    public List<Listing> getByUser(String user) {
        return listingDataSource.findByPublisher(user);
    }
}
