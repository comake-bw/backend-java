package com.lambdaschool.comake.services;

import com.lambdaschool.comake.ComakeApplicationTest;
import com.lambdaschool.comake.exceptions.ResourceNotFoundException;
import com.lambdaschool.comake.models.*;
import com.lambdaschool.comake.repository.IssueRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComakeApplicationTest.class,
    properties = {
        "command.line.runner.enabled=false"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IssueServiceImplUnitNoDBTest
{
    @Autowired
    private IssueService issueService;

    @MockBean
    private IssueRepository issuerepos;

    private List<Issue> issueList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();




    @Before
    public void setUp() throws Exception
    {
        Role r1 = new Role("user");
        Role r2 = new Role("admin");

        r1.setRoleid(1);
        r2.setRoleid(2);

        Location l1 = new Location(111111);
        Location l2 = new Location(222222);
        Location l3 = new Location(333333);

        l1.setLocationid(1);
        l2.setLocationid(2);
        l3.setLocationid(3);

        // user1, user
        User u1 = new User("user1",
            "password", l1);
        u1.setUserid(10);
        u1.getRoles()
            .add(new UserRoles(u1, r1));

        userList.add(u1);

        // user 2, user + admin
        User u2 = new User("cinnamon",
            "1234567", l2);
        u2.setUserid(20);
        u2.getRoles()
            .add(new UserRoles(u2, r1));
        u2.getRoles()
            .add(new UserRoles(u2, r2));

        userList.add(u2);

        // user 3, user
        User u3 = new User("barnbarn",
            "carrot", l3);
        u3.setUserid(30);
        u3.getRoles()
            .add(new UserRoles(u3, r1));

        userList.add(u3);

        Issue i1 = new Issue("pot hole", "pothole.imgurl.here", u1, l1);
        Issue i2 = new Issue("highway clean up", "highway-cleanup.imgurl.here", u2, l2);
        Issue i3 = new Issue("restore historic cottage", "historic-cottage-restoration.imgurl.here", u3, l3);

        i1.setIssueid(11);
        i2.setIssueid(12);
        i3.setIssueid(13);

        u1.getUserissues().add(new UserIssues(u1, i1));
        u1.getUserissues().add(new UserIssues(u1, i2));
        u1.getUserissues().add(new UserIssues(u1, i3));

        u2.getUserissues().add(new UserIssues(u2, i2));
        u2.getUserissues().add(new UserIssues(u2, i3));

        u3.getUserissues().add(new UserIssues(u3, i3));

        issueList.add(i1);
        issueList.add(i2);
        issueList.add(i3);

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void findAll()
    {
        Mockito.when(issuerepos.findAll())
            .thenReturn(issueList);

        assertEquals(3, issueService.findAll().size());
    }

    @Test
    public void findIssueById()
    {
        Mockito.when(issuerepos.findById(7L))
            .thenReturn(Optional.of(issueList.get(0)));

        assertEquals("pot hole", issueService.findIssueById(7L).getDescription());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findIssueByIdNotFound()
    {
        Mockito.when(issuerepos.findById(7L))
            .thenReturn(Optional.empty());

        assertEquals("pot hole", issueService.findIssueById(7L).getDescription());
    }

    @Test
    public void delete()
    {
        Mockito.when(issuerepos.findById(7L))
            .thenReturn(Optional.of(issueList.get(0)));

        Mockito.doNothing().when(issuerepos).deleteById(7l);

        issueService.delete(7L);
        assertEquals(3, issueList.size());

    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteNotFound()
    {
        Mockito.when(issuerepos.findById(7L))
            .thenReturn(Optional.empty());

        Mockito.doNothing().when(issuerepos).deleteById(7l);

        issueService.delete(7L);
        assertEquals(3, issueList.size());
    }

    @Test
    public void update()
    {
    }

    @Test
    public void save()
    {
        // making an issue to test
        Role r1 = new Role("user");
        r1.setRoleid(1);

        Location l1 = new Location(111111);
        l1.setLocationid(1);

        // user1, user
        User u1 = new User("user1",
            "password", l1);
        u1.setUserid(10);
        u1.getRoles()
            .add(new UserRoles(u1, r1));

        Issue i1 = new Issue("pot hole", "pothole.imgurl.here", u1, l1);
        i1.setIssueid(11);

        Mockito.when(issuerepos.findById(11L))
            .thenReturn(Optional.of(i1));

        Mockito.when(issuerepos.save(any(Issue.class)))
            .thenReturn(i1);

        Issue addIssue = issueService.save(i1);
        assertNotNull(addIssue);
        assertEquals(i1.getDescription(),
            addIssue.getDescription());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void saveputNotFound()
    {
        // making an issue to test
        Role r1 = new Role("user");
        r1.setRoleid(1);

        Location l1 = new Location(111111);
        l1.setLocationid(1);

        // user1, user
        User u1 = new User("user1",
            "password", l1);
        u1.setUserid(10);
        u1.getRoles()
            .add(new UserRoles(u1, r1));

        Issue i1 = new Issue("pot hole", "pothole.imgurl.here", u1, l1);
        i1.setIssueid(11);

        Mockito.when(issuerepos.findById(11L))
            .thenReturn(Optional.empty());

        Mockito.when(issuerepos.save(any(Issue.class)))
            .thenReturn(i1);

        Issue addIssue = issueService.save(i1);
        assertNotNull(addIssue);
        assertEquals(i1.getDescription(),
            addIssue.getDescription());
    }

    @Test
    public void findListByUserid()
    {
    }

    @Test
    public void findListByLocationid()
    {
    }

    @Test
    public void findListByZipcode()
    {
    }

    @Test
    public void deleteAll()
    {
    }
}