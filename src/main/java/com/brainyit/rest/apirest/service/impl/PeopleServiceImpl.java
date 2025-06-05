package com.brainyit.rest.apirest.service.impl;

import com.brainyit.rest.apirest.exception.ResourceNotFoundException;
import com.brainyit.rest.apirest.model.Person;
import com.brainyit.rest.apirest.repository.PersonRepository;
import com.brainyit.rest.apirest.service.PeopleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PersonRepository personRepository;

    private Logger log = LoggerFactory.getLogger(PeopleServiceImpl.class.getName());

    PeopleServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Person not found"));

    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void delete(Long id) {
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        personRepository.delete(entity);
        personRepository.deleteById(id);
    }

    @Override
    public Person update(Person person) {
        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return personRepository.save(person);

    }
}
