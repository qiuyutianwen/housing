package com.cmpe202.demo.DataSource;

import com.cmpe202.demo.model.Listing;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ListingDataSource extends CrudRepository<Listing,String> {
    List<Listing> findByListingType(String listingType);
    List<Listing> findByPublisher(String user);
}
