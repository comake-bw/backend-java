package com.lambdaschool.comake.repository;

import com.lambdaschool.comake.models.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Long>
{

    Location findLocationByZipcode(long zipcode);
}
