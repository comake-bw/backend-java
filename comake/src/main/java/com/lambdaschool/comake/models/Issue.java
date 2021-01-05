package com.lambdaschool.comake.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "issues")
public class Issue extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long issueid;

    @Column(nullable = false)
    private String description;

    @Column
    private String imageurl;

    @ManyToOne
    @JoinColumn(name = "userid",
        nullable = false)
    @JsonIgnoreProperties(value = "issues",
        allowSetters = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "locationid",
        nullable = false)
    @JsonIgnoreProperties(value = {"issues", "users"},
        allowSetters = true)
    private Location location;

    public Issue()
    {
    }

    public Issue(
        String description,
        String imageurl,
        User user,
        Location location)
    {
        this.description = description;
        this.imageurl = imageurl;
        this.user = user;
        this.location = location;
    }

    public long getIssueid()
    {
        return issueid;
    }

    public void setIssueid(long issueid)
    {
        this.issueid = issueid;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getImageurl()
    {
        return imageurl;
    }

    public void setImageurl(String imageurl)
    {
        this.imageurl = imageurl;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }
}
