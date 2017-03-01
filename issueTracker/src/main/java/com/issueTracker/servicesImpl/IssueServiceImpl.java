package com.issueTracker.servicesImpl;

import com.issueTracker.entities.Issue;
import com.issueTracker.mapper.ModelParser;
import com.issueTracker.models.IssueModel;
import com.issueTracker.repositories.IssueRepository;
import com.issueTracker.services.IssueService;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Local(IssueService.class)
public class IssueServiceImpl implements IssueService {

    @Inject
    private IssueRepository issueRepository;

    @Inject
    private ModelParser modelParser;

    @Override
    public void create(IssueModel issueModel) {

        Issue issue = this.modelParser.convert(issueModel, Issue.class);
        this.issueRepository.create(issue);
    }

    @Override
    public void edit(IssueModel issue, String issueName, String status, String priority) {

        Issue currIssue = this.issueRepository.getById(issue.getId());
        this.issueRepository.edit(currIssue, issueName, status, priority);
    }

    @Override
    public void delete(IssueModel issueModel) {

        Issue issue = this.issueRepository.getById(issueModel.getId());
        this.issueRepository.delete(issue);
    }

    @Override
    public IssueModel getById(Long id) {

        Issue issue = this.issueRepository.getById(id);
        IssueModel issueModel = null;

        if (issue != null) {

            issueModel = this.modelParser.convert(issue, IssueModel.class);
        }

        return issueModel;
    }

    @Override
    public List<IssueModel> findAllByUserId(Long id) {

        List<Issue> issues = this.issueRepository.findAllByUserId(id);
        List<IssueModel> issueModels = new ArrayList<>();

        for (Issue issue : issues) {

            IssueModel issueModel = this.modelParser.convert(issue, IssueModel.class);
            issueModels.add(issueModel);
        }
        return issueModels;
    }

    @Override
    public List<IssueModel> getAllIssues() {

        List<Issue> issues = this.issueRepository.getAllIssues();
        List<IssueModel> issueModels = new ArrayList<>();

        for (Issue issue : issues) {

            IssueModel issueModel = this.modelParser.convert(issue, IssueModel.class);
            issueModels.add(issueModel);
        }

        return issueModels;
    }
}
