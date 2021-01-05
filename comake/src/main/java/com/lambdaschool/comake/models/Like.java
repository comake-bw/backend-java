//package com.lambdaschool.comake.models;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "likes")
//public class Like extends Auditable
//{
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long likeid;
//
//    @ManyToOne
//    @JoinColumn(name = "userid",
//        nullable = false)
//    @JsonIgnoreProperties(value = "like",
//        allowSetters = true)
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "issueid",
//        nullable = false)
//    @JsonIgnoreProperties(value = "like",
//        allowSetters = true)
//    private Issue issue;
//
//    public Like()
//    {
//    }
//
//    public Like(
//        User user,
//        Issue issue)
//    {
//        this.user = user;
//        this.issue = issue;
//    }
//
//    public long getLikeid()
//    {
//        return likeid;
//    }
//
//    public void setLikeid(long likeid)
//    {
//        this.likeid = likeid;
//    }
//
//    public User getUser()
//    {
//        return user;
//    }
//
//    public void setUser(User user)
//    {
//        this.user = user;
//    }
//
//    public Issue getIssue()
//    {
//        return issue;
//    }
//
//    public void setIssue(Issue issue)
//    {
//        this.issue = issue;
//    }
//}
