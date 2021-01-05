package com.lambdaschool.comake.controllers;

import com.lambdaschool.comake.models.Issue;
import com.lambdaschool.comake.repository.UserIssuesRepository;
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


//    // POST http://localhost:2019/posts/post/{issueid} upvote an issue
//    @PostMapping(value = "/post/issueid", consumes = "application/json")
//    public ResponseEntity<?> addNewPost(@Valid
//                                        @RequestBody
//                                            long issueid) throws
//                                                            URISyntaxException
//    {
//        newIssue.setIssueid(0);
//        newIssue.setUser(userService.findByName(SecurityContextHolder.getContext()
//            .getAuthentication().getName()));
//        newIssue.setLocation(newIssue.getUser().getLocation());
//
//        newIssue = issueService.save(newIssue);
//
//        // set the location header for the newly created resource
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI newBookURI = ServletUriComponentsBuilder.fromCurrentRequest()
//            .path("/{issueid}")
//            .buildAndExpand(newIssue.getIssueid())
//            .toUri();
//        responseHeaders.setLocation(newBookURI);
//
//        return new ResponseEntity<>(null,
//            responseHeaders,
//            HttpStatus.CREATED);
//    }

}
