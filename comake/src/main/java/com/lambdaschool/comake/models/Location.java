package com.lambdaschool.comake.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "locations")
public class Location extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long locationid;

    @Column(nullable = false, unique = true)
    private long zipcode;

    @OneToMany(mappedBy = "location",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties({"location", "issues", "roles"})
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "location",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties("location")
    private Set<Issue> issues = new HashSet<>();


    public Location()
    {
    }

    public Location(
        long zipcode)
    {
        this.zipcode = zipcode;
    }

    public long getLocationid()
    {
        return locationid;
    }

    public void setLocationid(long locationid)
    {
        this.locationid = locationid;
    }

    public long getZipcode()
    {
        return zipcode;
    }

    public void setZipcode(long zipcode)
    {
        this.zipcode = zipcode;
    }

    public Set<User> getUsers()
    {
        return users;
    }

    public void setUsers(Set<User> users)
    {
        this.users = users;
    }

    public Set<Issue> getIssues()
    {
        return issues;
    }

    public void setIssues(Set<Issue> issues)
    {
        this.issues = issues;
    }
}
