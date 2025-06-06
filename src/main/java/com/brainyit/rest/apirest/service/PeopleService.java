package com.brainyit.rest.apirest.service;

import com.brainyit.rest.apirest.dto.v1.PersonDTO;
import com.brainyit.rest.apirest.dto.v2.PersonDTOV2;

import java.util.List;


public interface PeopleService {
    public PersonDTO findById(Long id);
    public List<PersonDTO> findAll();
    public PersonDTO create(PersonDTO person);
    public void delete(Long id);
    public PersonDTO update(PersonDTO person);

    PersonDTOV2 createV2(PersonDTOV2 personDTOV2);
    public PersonDTO disablePerson(Long id);
}
