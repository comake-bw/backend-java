package com.lambdaschool.comake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


/**
 * Main class to start the application.
 */
//@EnableJpaAuditing
@SpringBootApplication
public class ComakeApplicationTest
{

    public static void main(String[] args)
    {

            SpringApplication.run(ComakeApplicationTest.class,
                args);
    }
}
