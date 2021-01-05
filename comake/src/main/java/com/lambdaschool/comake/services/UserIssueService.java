package com.lambdaschool.comake.services;

import com.lambdaschool.comake.models.Issue;
import com.lambdaschool.comake.models.UserIssues;

public interface UserIssueService
{
    UserIssues save(UserIssues userIssue);
}
