package com.lambdaschool.comake.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The entity allowing interaction with the userposts table.
 * The join table between users and posts.
 */
@Entity
@Table(name = "userissues")
@IdClass(UserIssuesId.class)
public class UserIssues
    extends Auditable
    implements Serializable
{
    /**
     * 1/2 of the primary key (long) for restaurantpayments.
     * Also is a foreign key into the restaurants table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "issues",
        allowSetters = true)
    private User user;

    /**
     * 1/2 of the primary key (long) for restaurantpayments.
     * Also is a foreign key into the payments table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "issueid")
    @JsonIgnoreProperties(value = "users",
        allowSetters = true)
    private Issue issue;

    /**
     * Default constructor required by JPA
     */
    public UserIssues()
    {
    }

    /**
     * Given a restaurant and payment object, create a join between them
     *
     * @param restaurant The restaurant of this relationship
     * @param payment    The payment of this relationship
     */
    public UserIssues(
        User user,
        Issue issue)
    {
        this.user = user;
        this.issue = issue;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Issue getIssue()
    {
        return issue;
    }

    public void setIssue(Issue issue)
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
        if (!(o instanceof UserIssues))
        {
            return false;
        }
        UserIssues that = (UserIssues) o;
        return ((user == null) ? 0 : user.getUserid()) == ((that.user == null) ? 0 : that.user.getUserid()) &&
            ((issue == null) ? 0 : issue.getIssueid()) == ((that.issue == null) ? 0 : that.issue.getIssueid());
    }

    @Override
    public int hashCode()
    {
        return 37;
    }
}

