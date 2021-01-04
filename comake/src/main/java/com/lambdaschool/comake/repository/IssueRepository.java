package com.lambdaschool.comake.repository;

import com.lambdaschool.comake.models.Issue;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IssueRepository extends CrudRepository<Issue, Long>
{
    List<Issue> findAllByUser_Username(String username);


}
