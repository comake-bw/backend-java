package com.lambdaschool.comake.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "userlocations")
@IdClass(UserLocationsId.class)
public class UserLocations  extends Auditable implements Serializable
{
    /**
     * 1/2 of the primary key (long) for restaurantpayments.
     * Also is a foreign key into the restaurants table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "locations",
        allowSetters = true)
    private User user;

    /**
     * 1/2 of the primary key (long) for restaurantpayments.
     * Also is a foreign key into the payments table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "locationid")
    @JsonIgnoreProperties(value = "users",
        allowSetters = true)
    private Location location;

    public UserLocations()
    {
    }

    public UserLocations(
        User user,
        Location location)
    {
        this.user = user;
        this.location = location;
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof UserLocations))
        {
            return false;
        }
        UserLocations that = (UserLocations) o;
        return ((user == null) ? 0 : user.getUserid()) == ((that.user == null) ? 0 : that.user.getUserid()) &&
            ((location == null) ? 0 : location.getLocationid()) == ((that.location == null) ? 0 : that.location.getLocationid());
    }

    @Override
    public int hashCode()
    {
        return 37;
    }
}
