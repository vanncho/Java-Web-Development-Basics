package com.issueTracker.repositories;

import com.issueTracker.entities.Issue;

import java.util.List;

public interface IssueRepository {

    void create(Issue issue);

    void edit(Issue issue, String issueName, String status, String priority);

    void delete(Issue issue);

    Issue getById(Long id);

    List<Issue> findAllByUserId(Long id);

    List<Issue> getAllIssues();
}
