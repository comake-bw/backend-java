package com.lambdaschool.comake.services;

import com.lambdaschool.comake.exceptions.ResourceFoundException;
import com.lambdaschool.comake.exceptions.ResourceNotFoundException;
import com.lambdaschool.comake.models.Location;
import com.lambdaschool.comake.repository.LocationRepository;
import com.lambdaschool.comake.repository.locationrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("locationService")
public class LocationServiceImpl implements LocationService
{
    @Autowired
    LocationRepository locationrepos;

    @Override
    public List<Location> findAll()
    {
        List<Location> list = new ArrayList<>();
        locationrepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;

    }

    @Override
    public Location findLocationById(long id)
    {
        return locationrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Location with id " + id + " Not Found!"));
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        Location goodbyeLocation = findLocationById(id);
        if (goodbyeLocation != null)
        {
            if (goodbyeLocation.getUsers()
                .size() > 0 || goodbyeLocation.getIssues().size() > 0)
            {
                throw new ResourceFoundException("Locations containing users/issues cannot be deleted. Move the users/issues to a new Location first");
            } else
            {
                locationrepos.deleteById(id);
            }
        } else
        {
            throw new ResourceNotFoundException("Location with id " + id + " Not Found!");
        }
    }

    @Transactional
    @Override
    public Location save(Location Location)
    {
        if (Location.getIssues()
            .size() > 0 || Location.getUsers().size() > 0)
        {
            throw new ResourceFoundException("Issues/users are not added through Locations.");
        }

        Location newLocation = new Location();

        newLocation.setZipcode(Location.getZipcode());

        return locationrepos.save(newLocation);
    }

    @Transactional
    @Override
    public Location update(Location Location,
                          long id)
    {
        Location currentLocation = findLocationById(id);

        if (Location.getIssues().size() > 0 || Location.getUsers().size() > 0)
        {
            throw new ResourceFoundException("Issues/users are not updated through Locations.");
        }

        if (Location.getZipcode() != 0)
        {
            currentLocation.setZipcode(Location.getZipcode());
        }

        return locationrepos.save(currentLocation);
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        locationrepos.deleteAll();
    }
}
