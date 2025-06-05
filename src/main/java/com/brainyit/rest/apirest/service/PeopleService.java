package com.brainyit.rest.apirest.service;

import com.brainyit.rest.apirest.model.Person;

import java.util.List;

public interface PeopleService {
    public Person findById(Long id);
    public List<Person> findAll();
    public Person create(Person person);
    public void delete(Long id);
    public Person update(Person person);
}
