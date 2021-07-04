package com.cmpe202.demo.DataSource;

import com.cmpe202.demo.idclass.ApplicationId;
import com.cmpe202.demo.model.Application;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationDataSource extends CrudRepository<Application, ApplicationId> {
    List<Application> findByApplicant(String applicant);
    List<Application> findByListingId(String listingId);
}
