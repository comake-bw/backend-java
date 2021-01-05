package com.lambdaschool.comake;

import com.lambdaschool.comake.models.*;
import com.lambdaschool.comake.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@Component
public class SeedData
    implements CommandLineRunner
{
    /**
     * Connects the Role Service to this process
     */
    @Autowired
    RoleService roleService;

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    @Autowired
    LocationService locationService;

    @Autowired
    IssueService issueService;


    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args) throws
                                   Exception
    {
        Role r1 = new Role("user");
        Role r2 = new Role("admin");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);

        Location l1 = new Location(111111);
        Location l2 = new Location(222222);
        Location l3 = new Location(333333);

        l1 = locationService.save(l1);
        l2 = locationService.save(l2);
        l3 = locationService.save(l3);




        // user1, user
        User u1 = new User("user1",
            "password", l1);
        u1.getRoles()
            .add(new UserRoles(u1, r1));


        u1 = userService.save(u1);

        // user 2, user + admin
        User u2 = new User("cinnamon",
            "1234567", l2);
        u2.getRoles()
            .add(new UserRoles(u2, r1));
        u2.getRoles()
            .add(new UserRoles(u2, r2));

        u2 = userService.save(u2);

        // user 3, user
        User u3 = new User("barnbarn",
            "carrot", l3);
        u3.getRoles()
            .add(new UserRoles(u3, r1));

        u3 = userService.save(u3);

        Issue i1 = new Issue("pot hole", "pothole.imgurl.here", 1, l1);
        Issue i2 = new Issue("highway clean up", "highway-cleanup.imgurl.here", 2, l2);
        Issue i3 = new Issue("restore historic cottage", "historic-cottage-restoration.imgurl.here", 3, l3);

        i1 = issueService.save(i1);
        i2 = issueService.save(i2);
        i3 = issueService.save(i3);

        u1.getUserissues().add(new UserIssues(u1, i1));
        u1.getUserissues().add(new UserIssues(u1, i2));
        u1.getUserissues().add(new UserIssues(u1, i3));

        u2.getUserissues().add(new UserIssues(u2, i2));
        u2.getUserissues().add(new UserIssues(u2, i3));

        u3.getUserissues().add(new UserIssues(u3, i3));

        u1 = userService.save(u1);
        u2 = userService.save(u2);
        u3 = userService.save(u3);


    }
}
