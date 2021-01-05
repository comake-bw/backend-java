package com.lambdaschool.comake.services;

import com.lambdaschool.comake.exceptions.ResourceNotFoundException;
import com.lambdaschool.comake.models.Location;
import com.lambdaschool.comake.repository.LocationRepository;
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
        locationrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Location id " + id + " not found!"));

        locationrepos.deleteById(id);
    }

    @Transactional
    @Override
    public Location save(Location location)
    {
        Location newLocation = new Location();

        if (location.getLocationid() != 0) {
            newLocation = locationrepos.findById(location.getLocationid())
                .orElseThrow(() -> new ResourceNotFoundException("Location id " + location.getLocationid() + " not found!"));
        }

        newLocation.setZipcode(location.getZipcode());
        newLocation.setIssues(location.getIssues());
        newLocation.setUsers(location.getUsers());

        return locationrepos.save(newLocation);
    }

    @Transactional
    @Override
    public Location update(Location Location,
                          long id)
    {
        Location currentLocation = findLocationById(id);

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
