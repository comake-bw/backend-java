package com.lambdaschool.comake.services;

import com.lambdaschool.comake.exceptions.ResourceNotFoundException;
import com.lambdaschool.comake.models.Issue;
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
        if (location.getZipcode() != 0) {
            newLocation.setZipcode(location.getZipcode());
        }

//        if (location.getIssues().size() > 0) {
//            newLocation.getIssues().clear();
//            for (Issue issue : location.getIssues()) {
//                Issue newIssue = new Issue();
//                newIssue.getUser()
//        }


//        if (employee.getJobtitles().size() > 0)
//        {
//            currentEmployee.getJobtitles()
//                .clear();
//            for (JobTitle jt : employee.getJobtitles())
//            {
//                JobTitle newJT = jtrepos.findById(jt.getJobtitleid())
//                    .orElseThrow(() -> new EntityNotFoundException("JobTitle " + jt.getJobtitleid() + " Not Found"));
//
//                currentEmployee.getJobtitles()
//                    .add(newJT);
//            }

        newLocation.setIssues(location.getIssues());
        newLocation.setUsers(location.getUsers());

        return locationrepos.save(newLocation);
    }

    @Transactional
    @Override
    public Location update(Location location, long id)
    {
        Location currentLocation = locationrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Location " + id + " not found!"));

        if (location.getZipcode() != 0)
        {
            currentLocation.setZipcode(location.getZipcode());
        }

        if (location.getIssues().size() > 0) {

        }

        //        if (employee.getJobtitles().size() > 0)
        //        {
        //            currentEmployee.getJobtitles()
        //                .clear();
        //            for (JobTitle jt : employee.getJobtitles())
        //            {
        //                JobTitle newJT = jtrepos.findById(jt.getJobtitleid())
        //                    .orElseThrow(() -> new EntityNotFoundException("JobTitle " + jt.getJobtitleid() + " Not Found"));
        //
        //                currentEmployee.getJobtitles()
        //                    .add(newJT);
        //            }


        if (location.getUsers().size() > 0) {

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
