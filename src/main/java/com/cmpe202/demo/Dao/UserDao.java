package com.cmpe202.demo.Dao;

import com.cmpe202.demo.DataSource.UserDataSource;
import com.cmpe202.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserDao {
    @Autowired
    private UserDataSource userDataSource;

    public List<User> getAll() {
        return StreamSupport.stream(userDataSource.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public User findByUserName(String userName) {
        return userDataSource.findById(userName).orElse(null);
    }

    public Boolean updateUserByUserName(User user) {
        if (userDataSource.existsById(user.getUserName())){
            userDataSource.deleteById(user.getUserName());
            userDataSource.save(user);
            return true;
        }
        return false;
    }

    public Boolean insertUser(User user) {
        if (!user.getRole().equals("normal"))
            user.setApproved(false);
        else
            user.setApproved(true);
        if (userDataSource.existsById(user.getUserName())){
            return false;
        }
        else {
            userDataSource.save(user);
            return true;
        }
    }

    public Boolean deleteUserByUserName(String userName) {
        if (!userDataSource.existsById(userName)){
            return false;
        }
        else {
            userDataSource.deleteById(userName);
            return true;
        }
    }

    public List<User> findByApproved(Boolean approved) {
        return userDataSource.findByApproved(approved);
    }
}
