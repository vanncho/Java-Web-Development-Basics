package com.issueTracker.models;

import com.issueTracker.entities.enums.Role;

import java.util.Set;

public class UserModel {

    private long id;

    private String username;

    private String fullName;

    private String password;

    private Role role;

    private Set<IssueModel> issues;

    public UserModel() {
    }

    public UserModel(String username, String fullName, String password, Role role) {
        this.setUsername(username);
        this.setFullName(fullName);
        this.setPassword(password);
        this.setRole(role);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<IssueModel> getIssues() {
        return issues;
    }

    public void setIssues(Set<IssueModel> issues) {
        this.issues = issues;
    }
}
