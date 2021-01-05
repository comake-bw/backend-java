package com.lambdaschool.comake.models;

import java.io.Serializable;

public class UserIssuesId implements Serializable
{
    private long user;

    private long issue;

    public UserIssuesId()
    {
    }

    public long getUser()
    {
        return user;
    }

    public void setUser(long user)
    {
        this.user = user;
    }

    public long getIssue()
    {
        return issue;
    }

    public void setIssue(long issue)
    {
        this.issue = issue;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        UserIssuesId that = (UserIssuesId) o;
        return user == that.user &&
            issue == that.issue;
    }

    @Override
    public int hashCode()
    {
        return 37;
    }
}
