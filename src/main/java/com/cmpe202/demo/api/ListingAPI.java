package com.cmpe202.demo.api;

import com.cmpe202.demo.Dao.ListingDao;
import com.cmpe202.demo.Dao.UserDao;
import com.cmpe202.demo.model.Listing;
import com.cmpe202.demo.model.ListingSearch;
import com.cmpe202.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/listing")
@RestController
public class ListingAPI {
    private final ListingDao listingDao;
    private final UserDao userDao;

    @Autowired
    public ListingAPI(ListingDao listingDao, UserDao userDao) {
        this.listingDao = listingDao;
        this.userDao = userDao;
    }

    @PostMapping()
    public ResponseEntity<String> insertListing(@RequestBody Listing listing) {
        List<Listing> listings = listingDao.getByUser(listing.getPublisher());
        User user = userDao.findByUserName(listing.getPublisher());
        if (!listings.isEmpty() && user.getRole().equals("normal")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(listingDao.insertListing(listing), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Listing> getListing(@PathVariable("id")String id) {
        Listing listing = listingDao.getById(id);
        if (listing==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(listing,HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<String> updateListing(@RequestBody Listing listing) {
        if (listingDao.updateListing(listing)) {
            return new ResponseEntity<>("OK",HttpStatus.OK);
        }
        return new ResponseEntity<>("Listing not found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteListing(@PathVariable("id")String id) {
        if (listingDao.deleteListingById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Listing not found",HttpStatus.NOT_FOUND);
    }

    @PostMapping("/search")
    public List<Listing> searchListing(@RequestBody ListingSearch search) {
        List<Listing> list = listingDao.getByType(search.getListingType());

        list.removeIf(l -> l.getStatus().equals("closed"));

        if (search.getZip() != null)
            list.removeIf(l -> !l.getZip().equals(search.getZip()));
        if (search.getAddress() != null)
            list.removeIf(l -> !l.getAddress().contains(search.getAddress()));
        if (search.getPriceLow() != null && search.getPriceHigh() != null)
            list.removeIf(l -> ( l.getPrice()>search.getPriceHigh() || l.getPrice()<search.getPriceLow() ));
        if (search.getSqftLow() != null && search.getSqftHigh() != null)
            list.removeIf(l -> ( l.getSqft()>search.getSqftHigh() || l.getSqft()<search.getSqftLow() ));
        if (search.getBedroom() != null)
            list.removeIf(l -> l.getNumberOfBedroom()!=search.getBedroom());
        if (search.getBathroom() != null)
            list.removeIf(l -> l.getNumberOfBathroom()!=search.getBathroom());
        if (search.getFlooring() != null)
            list.removeIf(l -> !l.getFlooring().equals(search.getFlooring()));
        if (search.getHomeType() != null)
            list.removeIf(l -> !l.getType().equals(search.getHomeType()));
        if (search.getYear() != null)
            list.removeIf(l -> l.getYear() > search.getYear());
        if (search.getParking() != null)
            list.removeIf(l -> !l.getParking().equals(search.getParking()));

        return list;
    }


    @GetMapping("/all")
    public List<Listing> getAll() {
        return listingDao.getALl();
    }

    @GetMapping("/user/{name}")
    public ResponseEntity<List<Listing>> getByUser(@PathVariable("name")String user) {
        List<Listing> listings = listingDao.getByUser(user);
        if (listings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listings,HttpStatus.OK);
    }
}
