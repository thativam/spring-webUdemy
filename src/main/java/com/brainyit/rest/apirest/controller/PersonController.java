package com.brainyit.rest.apirest.controller;

import com.brainyit.rest.apirest.dto.v1.PersonDTO;
import com.brainyit.rest.apirest.dto.v2.PersonDTOV2;
import com.brainyit.rest.apirest.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PeopleService service;

    @GetMapping
    public List<PersonDTO> findAll() {
        List<PersonDTO> list = service.findAll();
        return list;
    }

    @GetMapping(value = "/{id}")
    public PersonDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    public PersonDTO create(@RequestBody PersonDTO PersonDTO) {
        return service.create(PersonDTO);
    }

    @PostMapping("/v2")
    public PersonDTOV2 createV2(@RequestBody PersonDTOV2 personDTOV2) {
        return service.createV2(personDTOV2);
    }

    @PutMapping
    public PersonDTO update(@RequestBody PersonDTO PersonDTO) {
        return service.update(PersonDTO);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}