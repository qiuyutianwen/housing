package com.cmpe202.demo.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {
    @Id
    private String id;

    private String recipient;
    private String body;
    private String status = "new";

    public Message() {
    }

    public Message(String id, String recipient, String body, String status) {
        this.id = id;
        this.recipient = recipient;
        this.body = body;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
