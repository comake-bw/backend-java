package com.lambdaschool.comake.services;

import com.lambdaschool.comake.models.Like;
import com.lambdaschool.comake.models.Location;

import java.util.List;

public interface LikeService
{
    List<Like> findAll();

    List<Like> findLikesByUserid(long id);

    List<Like> findLikesByPostid(long id);

    Like findLikeById(long id);

    void delete(long id);

    Like save(Like role);

    Like update(Like role, long id);

    void deleteAll();
}
