package com.lambdaschool.comake.controllers;

import com.lambdaschool.comake.models.Issue;
import com.lambdaschool.comake.models.Location;
import com.lambdaschool.comake.services.IssueService;
import com.lambdaschool.comake.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController
{
    @Autowired
    LocationService locationService;

    // GET http://localhost:2019/locations/locations get all locations
    @GetMapping(value = "/locations",
        produces = {"application/json"})
    public ResponseEntity<?> listAllLocations(HttpServletRequest request)
    {
        List<Location> rtnList = locationService.findAll();
        return new ResponseEntity<>(rtnList,
            HttpStatus.OK);
    }


    // GET http://localhost:2019/locations/location/{id} get location by id
    @GetMapping(value = "/location/{id}",
        produces = {"application/json"})
    public ResponseEntity<?> getLocationById(HttpServletRequest request,
                                              @PathVariable
                                                  Long id)
    {
        Location location = locationService.findLocationById(id);
        return new ResponseEntity<>(location,
            HttpStatus.OK);
    }

    // POST http://localhost:2019/locations/location add new post
    @PostMapping(value = "/location", consumes = "application/json")
    public ResponseEntity<?> addNewLocation(@Valid
                                        @RequestBody Location newLocation) throws
                                                                     URISyntaxException
    {
        newLocation.setLocationid(0);
        newLocation = locationService.save(newLocation);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newBookURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{locationid}")
            .buildAndExpand(newLocation.getLocationid())
            .toUri();
        responseHeaders.setLocation(newBookURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }


    // PUT http://localhost:2019/posts/post/{postid} edit entire post
    @PutMapping(value = "/location/{locationid}",
        consumes = "application/json")
    public ResponseEntity<?> updateLocation(
        @Valid
        @RequestBody
            Location updateLocation, @PathVariable long locationid)
    {
        updateLocation.setLocationid(locationid);
        locationService.save(updateLocation);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // DELETE http://localhost:2019/posts/post delete entire post
    @DeleteMapping(value = "/location/{id}")
    public ResponseEntity<?> deleteLocationByid(@PathVariable long id)
    {
        locationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/location/{locationid}",
        consumes = {"application/json"})
    public ResponseEntity<?> updateEmployee(
        @RequestBody
            Location updateLocation,
        @PathVariable
            long locationid)
    {
        locationService.update(updateLocation,
            locationid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
