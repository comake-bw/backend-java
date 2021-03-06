package com.lambdaschool.comake.controllers;

import com.lambdaschool.comake.models.Issue;
import com.lambdaschool.comake.models.Location;
import com.lambdaschool.comake.services.IssueService;
import com.lambdaschool.comake.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class IssueController
{
    @Autowired
    IssueService issueService;

    @Autowired
    UserService userService;

    // GET http://localhost:2019/posts/posts get all posts
    @GetMapping(value = "/posts",
        produces = {"application/json"})
    public ResponseEntity<?> listAllPosts(HttpServletRequest request)
    {
        List<Issue> myPosts = issueService.findAll();
        return new ResponseEntity<>(myPosts,
            HttpStatus.OK);
    }

    // GET http://localhost:2019/posts/posts/{locationid} get all posts in location id
    @GetMapping(value = "/location/{locationid}",
        produces = {"application/json"})
    public ResponseEntity<?> getPostsByLocationid(HttpServletRequest request,
                                              @PathVariable
                                                  Long locationid)
    {
        List<Issue> rtnList = issueService.findListByLocationid(locationid);
        return new ResponseEntity<>(rtnList,
            HttpStatus.OK);
    }

    // GET http://localhost:2019/posts/posts/{zipcode} get all posts in zipcode
    @GetMapping(value = "/zipcode/{zipcode}",
        produces = {"application/json"})
    public ResponseEntity<?> getPostsByZipcode(HttpServletRequest request,
                                              @PathVariable
                                                  Long zipcode)
    {
        List<Issue> rtnList = issueService.findListByZipcode(zipcode);
        return new ResponseEntity<>(rtnList,
            HttpStatus.OK);
    }

    // GET http://localhost:2019/posts/posts/{userid} get all posts by userid
    @GetMapping(value = "/userid/{userid}",
        produces = {"application/json"})
    public ResponseEntity<?> getPostsByUserid(HttpServletRequest request,
                                         @PathVariable
                                             Long userid)
    {
        List<Issue> rtnList = issueService.findListByUserid(userid);
        return new ResponseEntity<>(rtnList,
            HttpStatus.OK);
    }

    // POST http://localhost:2019/posts/post add new post
    @PostMapping(value = "/post", consumes = "application/json")
    public ResponseEntity<?> addNewPost(@Valid
                                        @RequestBody Issue newIssue) throws
                                                                   URISyntaxException
    {
        newIssue.setIssueid(0);
        newIssue.setUser(userService.findByName(SecurityContextHolder.getContext()
            .getAuthentication().getName()));
        newIssue.setLocation(newIssue.getUser().getLocation());

        newIssue = issueService.save(newIssue);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newBookURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{issueid}")
            .buildAndExpand(newIssue.getIssueid())
            .toUri();
        responseHeaders.setLocation(newBookURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }


    // PUT http://localhost:2019/posts/post/{postid} edit entire post
    @PutMapping(value = "/post/{postid}",
        consumes = "application/json")
    public ResponseEntity<?> updateFullPost(
        @Valid
        @RequestBody
            Issue updateIssue,
        @PathVariable
            long postid)
    {
        updateIssue.setIssueid(postid);

        updateIssue.setUser(userService.findByName(SecurityContextHolder.getContext()
            .getAuthentication().getName()));
        updateIssue.setLocation(updateIssue.getUser().getLocation());

        issueService.save(updateIssue);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // DELETE http://localhost:2019/posts/post delete entire post
    @DeleteMapping(value = "/post/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable long id)
    {
        issueService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/post/{postid}",
        consumes = {"application/json"})
    public ResponseEntity<?> updateIssue(
        @RequestBody
            Issue updateIssue,
        @PathVariable
            long postid)
    {
        issueService.update(updateIssue,
            postid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
