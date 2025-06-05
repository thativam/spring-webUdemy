package com.brainyit.rest.apirest.repository;

import com.brainyit.rest.apirest.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
