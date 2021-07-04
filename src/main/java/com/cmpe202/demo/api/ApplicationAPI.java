package com.cmpe202.demo.api;

import com.cmpe202.demo.Dao.ApplicationDao;
import com.cmpe202.demo.Dao.ListingDao;
import com.cmpe202.demo.Dao.MessageDao;
import com.cmpe202.demo.model.Application;
import com.cmpe202.demo.model.Listing;
import com.cmpe202.demo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/application")
@RestController
public class ApplicationAPI {
    private ApplicationDao applicationDao;
    private MessageDao messageDao;
    private ListingDao listingDao;

    @Autowired
    public ApplicationAPI(ApplicationDao applicationDao, MessageDao messageDao, ListingDao listingDao) {
        this.applicationDao = applicationDao;
        this.messageDao = messageDao;
        this.listingDao = listingDao;
    }

    @GetMapping("/byuser/{name}")
    public ResponseEntity<List<Application>> getApplicationByUser(@PathVariable("name")String name) {
        List<Application> applications = applicationDao.getApplicationByApplicant(name);
        if (applications.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(applications,HttpStatus.OK);
    }

    @GetMapping("/bylisting/{id}")
    public ResponseEntity<List<Application>> getApplicationByListing(@PathVariable("id")String id) {
        List<Application> applications = applicationDao.getApplicationByListId(id);
        if (applications.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(applications,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> insertApplication(@RequestBody Application application) {
        application.setAddress(listingDao.getById(application.getListingId()).getAddress());
        if (applicationDao.insertApplication(application)) {
            Message message = new Message();
            message.setId(UUID.randomUUID().toString());
            Listing listing = listingDao.getById(application.getListingId());
            message.setRecipient(listing.getPublisher());
            message.setBody("User " + application.getApplicant() + " has submitted an application to your listing at " + listing.getAddress());
            messageDao.insertMessage(message);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        return new ResponseEntity<>("Insert failed",HttpStatus.BAD_REQUEST);
    }

    @PutMapping()
    public ResponseEntity<String> updateApplication(@RequestBody Application application) {
        application.setAddress(listingDao.getById(application.getListingId()).getAddress());
        if (applicationDao.updateApplication(application)) {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        return new ResponseEntity<>("Update failed",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{name}/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable("name")String name,@PathVariable("id")String id) {
        if (applicationDao.deleteApplication(name,id)) {
            Message message = new Message();
            message.setRecipient(name);
            message.setBody("Sorry to inform you that your application for listing at "+ listingDao.getById(id).getAddress() + " has been declined.");
            messageDao.insertMessage(message);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete failed",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/approve/{name}/{id}")
    public ResponseEntity<String> approveApplication(@PathVariable("name")String name,@PathVariable("id")String id) {
        Application app = applicationDao.getApplication(name,id);

        if (app == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        app.setApproved(true);
        applicationDao.deleteApplication(app.getApplicant(),app.getListingId());
        applicationDao.insertApplication(app);

        Message message = new Message();
        message.setRecipient(name);
        message.setBody("Congratulation! Your application for listing at " + listingDao.getById(id).getAddress() + " has been approved!");
        messageDao.insertMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{name}/{id}")
    public ResponseEntity<Application> getApplication(@PathVariable("name")String user,@PathVariable("id")String id) {
        Application application = applicationDao.getApplication(user,id);
        if (application==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(application,HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Application> getALl() {
        return applicationDao.getALl();
    }
}
