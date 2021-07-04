package com.cmpe202.demo.DataSource;

import com.cmpe202.demo.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageDataSource extends CrudRepository<Message,String> {
    List<Message> findByRecipient(String user);
}
