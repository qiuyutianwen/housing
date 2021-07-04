package com.cmpe202.demo.api;

import com.cmpe202.demo.Dao.MessageDao;
import com.cmpe202.demo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/message")
@RestController
public class MessageAPI {
    private MessageDao messageDao;

    @Autowired
    public MessageAPI(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Message>> getMessage(@PathVariable("name") String name) {
        List<Message> messages = messageDao.getMessageByUser(name);
        if (messages.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(messages,HttpStatus.OK);

    }

    @PutMapping()
    public ResponseEntity<String> updateMessage(@RequestBody Message message) {
        if (messageDao.updateMessage(message)) {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        return new ResponseEntity<>("Update failed!",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public List<Message> getAll() {
        return messageDao.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable("id")String id) {
        if (messageDao.deleteMessage(id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/read/{id}")
    public ResponseEntity<String> markAsRead(@PathVariable("id") String id) {
        Message message = messageDao.getById(id);
        if (message==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        message.setStatus("read");
        messageDao.updateMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
