package com.lambdaschool.comake.services;

import com.lambdaschool.comake.exceptions.ResourceFoundException;
import com.lambdaschool.comake.exceptions.ResourceNotFoundException;
import com.lambdaschool.comake.models.Issue;
import com.lambdaschool.comake.models.UserIssues;
import com.lambdaschool.comake.repository.UserIssuesRepository;
import com.lambdaschool.comake.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service(value = "userissueService")
public class UserIssueServiceImpl implements UserIssueService
{

    @Autowired
    UserIssuesRepository userissuerepo;

    @Autowired
    UserRepository userrepos;

    @Transactional
    @Override
    public UserIssues save(UserIssues userIssue)
    {
        UserIssues newUserIssue = new UserIssues();
        newUserIssue.setIssue(userIssue.getIssue());
        newUserIssue.setUser(userIssue.getUser());
        return userissuerepo.save(newUserIssue);
    }


}
