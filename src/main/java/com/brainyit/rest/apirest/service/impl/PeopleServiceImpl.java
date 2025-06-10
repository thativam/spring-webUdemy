package com.brainyit.rest.apirest.service.impl;

import com.brainyit.rest.apirest.controller.PersonController;
import com.brainyit.rest.apirest.dto.v1.PersonDTO;
import com.brainyit.rest.apirest.dto.v2.PersonDTOV2;
import com.brainyit.rest.apirest.exception.RequiredObjectIsNullException;
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
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper objectMapper;

    private final Logger log = LoggerFactory.getLogger(PeopleServiceImpl.class.getName());

    @Override
    public PersonDTO findById(Long id) {
        Person p = personRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("PersonDTO not found"));
        PersonDTO dto = ObjectMapper.parseObject(p, PersonDTO.class);
        addHateoasLinks(dto);
        log.info("Added hateoas in find by id");
        return dto;

    }

    @Override
    public List<PersonDTO> findAll() {
        List<PersonDTO> dtos = ObjectMapper.parseListObjects(personRepository.findAll(), PersonDTO.class);
        dtos.forEach(this::addHateoasLinks);
        return dtos;
    }

    @Override
    public PersonDTO create(PersonDTO personDto) {
        if (personDto == null) throw new RequiredObjectIsNullException();
        PersonDTO dto = ObjectMapper.parseObject(personRepository.save(ObjectMapper.parseObject(personDto, Person.class)), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    @Override
    public void delete(Long id) {
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        personRepository.delete(entity);
    }

    @Override
    public PersonDTO update(PersonDTO personDto) {
        if (personDto == null) throw new RequiredObjectIsNullException();

        Person entity = personRepository.findById(personDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(personDto.getFirstName());
        entity.setLastName(personDto.getLastName());
        entity.setAddress(personDto.getAddress());
        entity.setGender(personDto.getGender());
        PersonDTO dto = ObjectMapper.parseObject(personRepository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;

    }

    @Override
    public PersonDTOV2 createV2(PersonDTOV2 personDTOV2) {

        return objectMapper.convertEntityToDTO(personRepository.save(objectMapper.convertDTOtoEntity(personDTOV2)));
    }

    @Transactional
    public PersonDTO disablePerson(Long id) {

        log.info("Disabling one Person!");

        personRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        personRepository.disablePerson(id);

        if(personRepository.findById(id).isEmpty()) throw  new ResourceNotFoundException("No records found for this ID!");
        Person entity = personRepository.findById(id).get();
        PersonDTO dto = ObjectMapper.parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

     private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).disablePerson(dto.getId())).withRel("disable").withType("PATCH"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    } 
}
