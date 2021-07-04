package com.cmpe202.demo.DataSource;

import com.cmpe202.demo.idclass.ApplicationId;
import com.cmpe202.demo.idclass.FavoriteId;
import com.cmpe202.demo.model.Favorite;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavoriteDataSource extends CrudRepository<Favorite, FavoriteId> {
    List<Favorite> findByUser(String user);
}
