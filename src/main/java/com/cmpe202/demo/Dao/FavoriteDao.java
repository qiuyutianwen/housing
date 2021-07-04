package com.cmpe202.demo.Dao;

import com.cmpe202.demo.DataSource.FavoriteDataSource;
import com.cmpe202.demo.idclass.ApplicationId;
import com.cmpe202.demo.idclass.FavoriteId;
import com.cmpe202.demo.model.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteDao {
    @Autowired
    private FavoriteDataSource favoriteDataSource;

    public List<Favorite> getByUser(String user) {
        return favoriteDataSource.findByUser(user);
    }

    public Boolean insert(Favorite favorite) {
        if (favoriteDataSource.existsById(new FavoriteId(favorite.getUser(),favorite.getListing()))) {
            return false;
        }
        favoriteDataSource.save(favorite);
        return true;
    }

    public Boolean delete(Favorite favorite) {
        FavoriteId id = new FavoriteId(favorite.getUser(),favorite.getListing());
        if (favoriteDataSource.existsById(id)) {
            favoriteDataSource.deleteById(id);
            return true;
        }
        return false;
    }

    public Favorite getFavorite(String name, String id) {
        return favoriteDataSource.findById(new FavoriteId(name,id)).orElse(null);
    }

}
