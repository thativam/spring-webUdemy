package com.brainyit.rest.apirest.service.impl;

import com.brainyit.rest.apirest.dto.v1.PersonDTO;
import com.brainyit.rest.apirest.dto.v2.PersonDTOV2;
import com.brainyit.rest.apirest.exception.ResourceNotFoundException;

import com.brainyit.rest.apirest.mapper.ObjectMapper;
import com.brainyit.rest.apirest.mapper.customMapper.PersonMapper;
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

    @Autowired
    private PersonMapper objectMapper;

    private Logger log = LoggerFactory.getLogger(PeopleServiceImpl.class.getName());

    PeopleServiceImpl(PersonRepository PersonRepository) {
        this.personRepository = PersonRepository;
    }

    @Override
    public PersonDTO findById(Long id) {
        Person p = personRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("PersonDTO not found"));
        return ObjectMapper.parseObject(p, PersonDTO.class);

    }

    @Override
    public List<PersonDTO> findAll() {
        return ObjectMapper.parseListObjects(personRepository.findAll(), PersonDTO.class);
    }

    @Override
    public PersonDTO create(PersonDTO PersonDTO) {
        return ObjectMapper.parseObject(personRepository.save(ObjectMapper.parseObject(PersonDTO, Person.class)), PersonDTO.class);
    }

    @Override
    public void delete(Long id) {
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        personRepository.delete(entity);
        personRepository.deleteById(id);
    }

    @Override
    public PersonDTO update(PersonDTO PersonDTO) {
        Person entity = personRepository.findById(PersonDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(PersonDTO.getFirstName());
        entity.setLastName(PersonDTO.getLastName());
        entity.setAddress(PersonDTO.getAddress());
        entity.setGender(PersonDTO.getGender());

        return ObjectMapper.parseObject(personRepository.save(entity), PersonDTO.class);

    }

    @Override
    public PersonDTOV2 createV2(PersonDTOV2 personDTOV2) {

        return objectMapper.convertEntityToDTO(personRepository.save(objectMapper.convertDTOtoEntity(personDTOV2)));
    }
}
