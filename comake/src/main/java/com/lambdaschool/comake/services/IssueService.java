package com.lambdaschool.comake.services;

import com.lambdaschool.comake.models.Issue;
import com.lambdaschool.comake.models.User;

import java.util.List;

public interface IssueService
{
    List<Issue> findAll();

    Issue findIssueById(long id);

    void delete(long id);

    Issue update(long issueid, Issue issue);

    Issue save(Issue issue);

    List <Issue> findListByUserid(long id);
    List <Issue> findListByLocationid(long id);
    List <Issue> findListByZipcode(long zipcode);

    void deleteAll();

}
