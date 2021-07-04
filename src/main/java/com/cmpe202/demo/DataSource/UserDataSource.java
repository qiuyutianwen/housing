package com.cmpe202.demo.DataSource;

import com.cmpe202.demo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDataSource extends CrudRepository<User,String> {
    List<User> findByApproved(Boolean approved);
}
