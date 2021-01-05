package com.lambdaschool.comake.services;

import com.lambdaschool.comake.exceptions.ResourceNotFoundException;
import com.lambdaschool.comake.models.Issue;
import com.lambdaschool.comake.models.Like;
import com.lambdaschool.comake.models.Location;
import com.lambdaschool.comake.models.User;
import com.lambdaschool.comake.repository.IssueRepository;
import com.lambdaschool.comake.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service(value = "issueService")
public class IssueServiceImpl implements IssueService
{
    @Autowired
    private IssueRepository issuerepos;

    @Autowired
    private IssueService issueService;

    @Autowired
    private LocationRepository locationrepos;


    @Override
    public List<Issue> findAll()
    {
        List<Issue> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        issuerepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Issue findIssueById(long id)
    {
        return issuerepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Issue id " + id + " not found!"));
    }

    @Override
    public void delete(long id)
    {
        issuerepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product id " + id + " not found!"));
        issuerepos.deleteById(id);
    }

    @Transactional
    @Override
    public Issue update(
        long id,
        Issue issue)
    {
        Issue currentIssue = issuerepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Issue id " + id + " not found!"));

        if (issue.getDescription() != null) {
            currentIssue.setDescription(issue.getDescription());
        }
        if (issue.getImageurl() != null) {
            currentIssue.setImageurl(issue.getImageurl());
        }

        return issuerepos.save(currentIssue);
    }

    @Transactional
    @Override
    public Issue save(Issue issue)
    {
        Issue newIssue = new Issue();

        if (issue.getIssueid() != 0) {
            newIssue = issuerepos.findById(issue.getIssueid())
                .orElseThrow(() -> new ResourceNotFoundException("Issue id " + issue.getIssueid() + " not found!"));
        }

        newIssue.setLocation(issue.getLocation());
        newIssue.setDescription(issue.getDescription());
        newIssue.setImageurl(issue.getImageurl());
        newIssue.setUser(issue.getUser());

        return issuerepos.save(newIssue);
    }

    @Override
    public List<Issue> findListByUserid(long id)
    {
        Iterable<Issue> list = issueService.findAll();
        List<Issue> filteredList = new ArrayList<>();
        for (Issue item: list) {
            if (item.getUser().getUserid() == id) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    @Override
    public List<Issue> findListByLocationid(long id)
    {
        Iterable<Issue> list = issueService.findAll();
        List<Issue> filteredList = new ArrayList<>();
        for (Issue item: list) {
            if (item.getLocation().getLocationid() == id) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    @Override
    public List<Issue> findListByZipcode(long zipcode)
    {
        Iterable<Issue> issueList = issueService.findAll();
        List<Issue> filteredList = new ArrayList<>();
        for (Issue item: issueList) {
            if (item.getLocation().getZipcode() == zipcode) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        issuerepos.deleteAll();
    }
}
