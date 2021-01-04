package com.lambdaschool.comake.controllers;

import com.lambdaschool.comake.models.Issue;
import com.lambdaschool.comake.models.Like;
import com.lambdaschool.comake.services.IssueService;
import com.lambdaschool.comake.services.LikeService;
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
@RequestMapping("/likes")
public class LikeController
{
    @Autowired
    LikeService likeService;

    // GET http://localhost:2019/likes/likes get all likes
    @GetMapping(value = "/likes",
        produces = {"application/json"})
    public ResponseEntity<?> listAllLikes(HttpServletRequest request)
    {
        List<Like> myLikes = likeService.findAll();
        return new ResponseEntity<>(myLikes,
            HttpStatus.OK);
    }


    // GET http://localhost:2019/likes/likes/{postid} get all likes by postid
    @GetMapping(value = "/likes/{postid}",
        produces = {"application/json"})
    public ResponseEntity<?> getLikesByPostid(HttpServletRequest request,
                                               @PathVariable
                                                   Long postid)
    {
        List<Like> rtnList = likeService.findLikesByIssueid(postid);
        return new ResponseEntity<>(rtnList,
            HttpStatus.OK);
    }

    // GET http://localhost:2019/likes/likes/{userid} get all likes by userid
    @GetMapping(value = "/likes/{userid}",
        produces = {"application/json"})
    public ResponseEntity<?> getLikesByUserid(HttpServletRequest request,
                                              @PathVariable
                                                  Long userid)
    {
        List<Like> rtnList = likeService.findLikesByUserid(userid);
        return new ResponseEntity<>(rtnList,
            HttpStatus.OK);
    }

    // POST http://localhost:2019/likes/like add new like
    @PostMapping(value = "/like", consumes = "application/json")
    public ResponseEntity<?> addNewLike(@Valid
                                        @RequestBody
                                            Like newLike) throws
                                                            URISyntaxException
    {
        newLike.setLikeid(0);
        newLike = likeService.save(newLike);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newBookURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{likeid}")
            .buildAndExpand(newLike.getLikeid())
            .toUri();
        responseHeaders.setLocation(newBookURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }


    // DELETE http://localhost:2019/likes/like/{id} delete a like
    @DeleteMapping(value = "/like/{id}")
    public ResponseEntity<?> deleteLikeByid(@PathVariable long id)
    {
        likeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
