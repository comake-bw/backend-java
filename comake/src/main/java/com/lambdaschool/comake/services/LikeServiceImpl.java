package com.lambdaschool.comake.services;

import com.lambdaschool.comake.exceptions.ResourceFoundException;
import com.lambdaschool.comake.exceptions.ResourceNotFoundException;
import com.lambdaschool.comake.models.Like;
import com.lambdaschool.comake.models.Like;
import com.lambdaschool.comake.repository.LikeRepository;
import com.lambdaschool.comake.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Transactional
@Service("likeService")
public class LikeServiceImpl implements LikeService
{
    @Autowired
    LikeRepository likerepos;

    @Override
    public List<Like> findAll()
    {
        List<Like> list = new ArrayList<>();
        likerepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;

    }

    @Override
    public Like findLikeById(long id)
    {
        return likerepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Like with id " + id + " Not Found!"));
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        Like goodbyeLike = findLikeById(id);
        if (goodbyeLike != null)
        {
            likerepos.deleteById(id);

        } else
        {
            throw new ResourceNotFoundException("Like with id " + id + " Not Found!");
        }
    }

    @Transactional
    @Override
    public Like save(Like Like)
    {
        Like newLike = new Like();

        return likerepos.save(newLike);
    }

    @Transactional
    @Override
    public Like update(Like Like, long id)
    {
        Like currentLike = findLikeById(id);
        return likerepos.save(currentLike);
    }

    @Override
    public List<Like> findLikesByUserid(long id)
    {
        Iterable<Like> list = likerepos.findAll();
        List<Like> filteredList = new ArrayList<>();
        for (Like item: list) {
            if (item.getUser().getUserid() == id) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    @Override
    public List<Like> findLikesByIssueid(long id)
    {
        Iterable<Like> list = likerepos.findAll();
        List<Like> filteredList = new ArrayList<>();
        for (Like item: list) {
            if (item.getIssue().getissueid() == id) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        likerepos.deleteAll();
    }
    
    
}
