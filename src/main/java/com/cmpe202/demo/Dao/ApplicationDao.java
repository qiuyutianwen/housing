package com.cmpe202.demo.Dao;

import com.cmpe202.demo.DataSource.ApplicationDataSource;
import com.cmpe202.demo.idclass.ApplicationId;
import com.cmpe202.demo.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ApplicationDao {
    @Autowired
    private ApplicationDataSource applicationDataSource;

    public List<Application> getALl() {
        return StreamSupport.stream(applicationDataSource.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public List<Application> getApplicationByApplicant(String applicant) {
        return applicationDataSource.findByApplicant(applicant);
    }

    public List<Application> getApplicationByListId(String id) {
        return applicationDataSource.findByListingId(id);
    }

    public Application getApplication(String user, String listing) {
        return applicationDataSource.findById(new ApplicationId(user,listing)).orElse(null);
    }

    public boolean insertApplication(Application application) {
        if (applicationDataSource.existsById(new ApplicationId(application.getApplicant(),application.getListingId()))) {
            return false;
        }
        applicationDataSource.save(application);
        return true;
    }

    public boolean updateApplication(Application application) {
        ApplicationId applicationId = new ApplicationId(application.getApplicant(),application.getListingId());
        if (applicationDataSource.existsById(applicationId)) {
            applicationDataSource.delete(application);
            applicationDataSource.save(application);
            return true;
        }
        return false;
    }

    public boolean deleteApplication(String applicant, String listId) {
        ApplicationId applicationId = new ApplicationId(applicant,listId);
        if (applicationDataSource.existsById(applicationId)) {
            applicationDataSource.deleteById(applicationId);
            return true;
        }
        return false;
    }

}
