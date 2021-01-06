package com.lambdaschool.comake.services;

import com.lambdaschool.comake.models.Issue;
import com.lambdaschool.comake.models.Location;
import com.lambdaschool.comake.models.User;

import java.util.List;

public interface LocationService
{
    List<Location> findAll();

    Location findLocationById(long id);

    void delete(long id);

    Location save(Location role);

    Location update(Location updateLocation, long id);

    void deleteAll();
}
