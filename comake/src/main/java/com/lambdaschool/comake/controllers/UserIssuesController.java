package com.lambdaschool.comake.controllers;

import com.lambdaschool.comake.models.Issue;
import com.lambdaschool.comake.models.User;
import com.lambdaschool.comake.models.UserIssues;
import com.lambdaschool.comake.repository.UserIssuesRepository;
import com.lambdaschool.comake.services.IssueService;
import com.lambdaschool.comake.services.UserIssueService;
import com.lambdaschool.comake.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/likes")
public class UserIssuesController
{
    @Autowired
    UserIssuesRepository userissuesrepo;

    @Autowired
    UserService userService;

    @Autowired
    IssueService issueService;

    @Autowired
    UserIssueService userIssueService;

        // GET http://localhost:2019/likes/like/{postid} get all likes by postid
        @GetMapping(value = "/like/{postid}",
            produces = {"application/json"})
        public ResponseEntity<?> getLikesByPostid(HttpServletRequest request,
                                                  @PathVariable Long postid)
        {
            long likeCount = userissuesrepo.findLikeCountByPostId(postid);
            return new ResponseEntity<>(likeCount,
                HttpStatus.OK);
        }


    // POST http://localhost:2019/likes/like/{postid} upvote an issue
    @PostMapping(value = "/like/{postid}")
    public ResponseEntity<?> addNewLike(@PathVariable long postid) throws
                                                            URISyntaxException
    {

        User currentUser = userService.findByName(SecurityContextHolder.getContext()
            .getAuthentication().getName());
//
        Issue currentIssue = issueService.findIssueById(postid);
//        currentUser.getUserissues().add(new UserIssues(currentUser,
//            currentIssue));
//
//        UserIssues newUserIssue = new UserIssues(currentUser, currentIssue);
//        userIssueService.save(newUserIssue);
//
        currentUser.getUserissues().add(new UserIssues(currentUser, currentIssue));

        userService.save(currentUser);

//        u1.getUserissues().add(new UserIssues(u1, i1));
//        u1.getUserissues().add(new UserIssues(u1, i2));
//        u1.getUserissues().add(new UserIssues(u1, i3));
//
//        u2.getUserissues().add(new UserIssues(u2, i2));
//        u2.getUserissues().add(new UserIssues(u2, i3));
//
//        u3.getUserissues().add(new UserIssues(u3, i3));
//
//        userService.save(u1);
//        userService.save(u2);
//        userService.save(u3);
//        newUserIssue = userIssueService.save(newUserIssue);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newBookURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{postid}")
            .buildAndExpand(currentUser.getUserid())
            .toUri();
        responseHeaders.setLocation(newBookURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }

}
