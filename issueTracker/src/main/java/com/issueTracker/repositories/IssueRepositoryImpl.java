package com.issueTracker.repositories;

import com.issueTracker.entities.Issue;
import com.issueTracker.entities.enums.Priority;
import com.issueTracker.entities.enums.Status;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local(IssueRepository.class)
public class IssueRepositoryImpl implements IssueRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Issue issue) {

        this.entityManager.persist(issue);
    }

    @Override
    public void edit(Issue issue, String issueName, String status, String priority) {

        Long id = issue.getId();
        Query query = this.entityManager.createQuery("UPDATE Issue AS i SET i.issueName = :issueName, i.status = :status, i.priority = :priority WHERE i.id = :id");
        query.setParameter("id", id);
        query.setParameter("issueName", issueName);
        Status s = Status.valueOf(status.toUpperCase());
        query.setParameter("status", s);
        Priority p = Priority.valueOf(priority.toUpperCase());
        query.setParameter("priority", p);

        query.executeUpdate();

    }

    @Override
    public void delete(Issue issue) {

        this.entityManager.remove(issue);
    }

    @Override
    public Issue getById(Long id) {

        Issue issue = this.entityManager.find(Issue.class, id);
        return issue;
    }

    @Override
    public List<Issue> findAllByUserId(Long id) {

        Query query = this.entityManager.createQuery("SELECT i FROM Issue AS i WHERE i.author.id = :id");
        query.setParameter("id", id);
        List<Issue> issues = query.getResultList();
        return issues;
    }

    @Override
    public List<Issue> getAllIssues() {

        Query query = this.entityManager.createQuery("SELECT i FROM Issue AS i");
        List<Issue> issues = query.getResultList();
        return issues;
    }
}
