package com.cmpe202.demo.api;

import com.cmpe202.demo.Dao.FavoriteDao;
import com.cmpe202.demo.Dao.ListingDao;
import com.cmpe202.demo.model.Favorite;
import com.cmpe202.demo.model.Listing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/favorite")
@RestController
public class FavoriteAPI {
    private final FavoriteDao favoriteDao;
    private final ListingDao listingDao;

    @Autowired
    public FavoriteAPI(FavoriteDao favoriteDao, ListingDao listingDao) {
        this.favoriteDao = favoriteDao;
        this.listingDao = listingDao;
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Listing>> getByUser(@PathVariable("name") String user) {
        List<Favorite> favorites = favoriteDao.getByUser(user);
        if (favorites.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<Listing> listings = new ArrayList<>();
        for (Favorite f:favorites) {
            listings.add(listingDao.getById(f.getListing()));
        }
        return new ResponseEntity<>(listings,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> insertFavorite(@RequestBody Favorite favorite) {
        if (favoriteDao.insert(favorite)) {
            return new ResponseEntity<>("OK",HttpStatus.OK);
        }
        return new ResponseEntity<>("Insert failed!",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{name}/{id}")
    public ResponseEntity<String> deleteFavorite(@PathVariable("name")String name,@PathVariable("id")String id) {
        Favorite favorite = favoriteDao.getFavorite(name,id);
        if (favoriteDao.delete(favorite)) {
            return new ResponseEntity<>("OK",HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete failed!",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{name}/{id}")
    public ResponseEntity<Favorite> checkFavorite(@PathVariable("name")String name,@PathVariable("id")String id) {
        Favorite favorite = favoriteDao.getFavorite(name,id);
        if (favorite != null)
            return new ResponseEntity<>(favorite,HttpStatus.OK);
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

}
