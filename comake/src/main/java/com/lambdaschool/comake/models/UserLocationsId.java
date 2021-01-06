//package com.lambdaschool.comake.models;
//
//import java.io.Serializable;
//
//public class UserLocationsId implements Serializable
//{
//    private long user;
//    private long location;
//
//    public UserLocationsId()
//    {
//    }
//
//    public long getUser()
//    {
//        return user;
//    }
//
//    public void setUser(long user)
//    {
//        this.user = user;
//    }
//
//    public long getLocation()
//    {
//        return location;
//    }
//
//    public void setLocation(long location)
//    {
//        this.location = location;
//    }
//
//    @Override
//    public boolean equals(Object o)
//    {
//        if (this == o)
//        {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass())
//        {
//            return false;
//        }
//        UserLocationsId that = (UserLocationsId) o;
//        return user == that.user &&
//            location == that.location;
//    }
//
//    @Override
//    public int hashCode()
//    {
//        return 37;
//    }
//}
