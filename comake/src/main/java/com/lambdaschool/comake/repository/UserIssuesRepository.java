package com.lambdaschool.comake.repository;

import com.lambdaschool.comake.models.UserIssues;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserIssuesRepository extends CrudRepository<UserIssues, Long>
{
    @Query(value = "SELECT COUNT(*) FROM USERISSUES\n" +
        "where issueid = :id",
    nativeQuery = true)
    long findLikeCountByPostId(long id);
}
