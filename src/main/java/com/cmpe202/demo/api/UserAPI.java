package com.cmpe202.demo.api;

import com.cmpe202.demo.Dao.UserDao;
import com.cmpe202.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserAPI {
    private UserDao userDao;

    @Autowired
    public UserAPI(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping()
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userDao.insertUser(user)){
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Account with this user name already exists",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/login/{name}/{pass}")
    public ResponseEntity<Map<String, String>> login(@PathVariable("name")String name,@PathVariable("pass")String password) {
        User user = userDao.findByUserName(name);
        HashMap<String, String> map = new HashMap<>();
        if (user == null){
            map.put("reason","User not found!");
            return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
        }
        else if (!user.getPassword().equals(password)){
            map.put("reason","Incorrect password!");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
        else if (!user.isApproved()){
            map.put("reason","Awaiting approval from admin!");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
        else{
            map.put("userName",user.getUserName());
            map.put("role",user.getRole());
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<User> getUser(@PathVariable("name")String name) {
        User user = userDao.findByUserName(name);
        if (user!=null){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PutMapping()
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        if (userDao.updateUserByUserName(user)){
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User not found!",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteUser(@PathVariable("name")String name) {
        if (userDao.deleteUserByUserName(name)){
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pending")
    public ResponseEntity<List<User>> getPendingUser() {
        List<User> users = userDao.findByApproved(false);
        if (users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @PutMapping("/approve/{name}")
    public ResponseEntity<String> approveUser(@PathVariable("name")String name) {
        User user = userDao.findByUserName(name);
        if (user==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        user.setApproved(true);
        userDao.updateUserByUserName(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getALl() {
        List<User> users = userDao.getAll();
        if (users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }


}
