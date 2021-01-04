package com.lambdaschool.comake.services;

import com.lambdaschool.comake.models.Issue;
import com.lambdaschool.comake.models.User;

import java.util.List;

public interface IssueService
{
    List<User> findAll();

    Issue findIssueById(long id);

    void delete(long id);

    Issue update(long issueid, Issue issue);

    Issue save(Issue issue);

}
