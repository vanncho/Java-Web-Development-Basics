package com.issueTracker.services;

import com.issueTracker.models.IssueModel;

import java.util.List;

public interface IssueService {

    void create(IssueModel issueModel);

    void edit(IssueModel issue, String issueName, String status, String priority);

    void delete(IssueModel issueModel);

    IssueModel getById(Long id);

    List<IssueModel> findAllByUserId(Long id);

    List<IssueModel> getAllIssues();
}
