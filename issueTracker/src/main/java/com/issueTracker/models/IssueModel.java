package com.issueTracker.models;

import com.issueTracker.entities.enums.Priority;
import com.issueTracker.entities.enums.Status;

import java.util.Date;

public class IssueModel {

    private long id;

    private String issueName;

    private Priority priority;

    private Status status;

    private Date createdOn;

    private UserModel author;

    public IssueModel() {

        this.setCreatedOn(new Date());
    }

    public IssueModel(String issueName, Priority priority, Status status) {
        this();
        this.setIssueName(issueName);
        this.setPriority(priority);
        this.setStatus(status);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }
}
