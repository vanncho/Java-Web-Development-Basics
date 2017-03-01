package com.issueTracker.entities;

import com.issueTracker.entities.enums.Priority;
import com.issueTracker.entities.enums.Status;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String issueName;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_on")
    private Date createdOn;

    @ManyToOne()
    @JoinColumn(name = "authorId")
    private User author;

    public Issue() {

        this.setCreatedOn(new Date());
    }

    public Issue(String issueName, Priority priority, Status status) {
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

    public void setCreatedOn(Date creationDate) {
        this.createdOn = creationDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
