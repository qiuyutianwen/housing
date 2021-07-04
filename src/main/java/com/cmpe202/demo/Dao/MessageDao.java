package com.cmpe202.demo.Dao;

import com.cmpe202.demo.DataSource.MessageDataSource;
import com.cmpe202.demo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MessageDao {
    @Autowired
    private MessageDataSource messageDataSource;

    public List<Message> getMessageByUser(String user) {
        return messageDataSource.findByRecipient(user);
    }

    public boolean insertMessage(Message message) {
        message.setId(UUID.randomUUID().toString());
        messageDataSource.save(message);
        return true;
    }

    public List<Message> getAll() {
        return StreamSupport.stream(messageDataSource.findAll().spliterator(),false).collect(Collectors.toList());
    }
    public boolean updateMessage(Message message) {
        if (messageDataSource.existsById(message.getId())) {
            messageDataSource.deleteById(message.getId());
            messageDataSource.save(message);
            return true;
        }
        return false;
    }

    public boolean deleteMessage(String id) {
        if (messageDataSource.existsById(id)){
            messageDataSource.deleteById(id);
            return true;
        }
        return false;
    }

    public Message getById(String id) {
        return messageDataSource.findById(id).orElse(null);
    }
}
