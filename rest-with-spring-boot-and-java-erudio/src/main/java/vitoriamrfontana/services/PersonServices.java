package vitoriamrfontana.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import vitoriamrfontana.controllers.PersonController;
import vitoriamrfontana.data.dto.v1.PersonDTO;
import vitoriamrfontana.data.dto.v2.PersonDTOV2;
import vitoriamrfontana.exception.ResourceNotFoundException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static vitoriamrfontana.mapper.ObjectMapper.parseListObjects;
import static vitoriamrfontana.mapper.ObjectMapper.parseObject;

import vitoriamrfontana.mapper.custom.PersonMapper;
import vitoriamrfontana.model.Person;
import vitoriamrfontana.repository.PersonRepository;

import java.util.List;


@Service
public class PersonServices {

    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;



    public  List<PersonDTO> findAll(){
        logger.info("Finding all People!");

       var persons = parseListObjects(repository.findAll(), PersonDTO.class);
       persons.forEach(this::addHateoasLinks);

        return persons;
    }


    public PersonDTO findById(Long id){
        logger.info("Find one Person!");

       var entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;

    }
    public PersonDTO create(PersonDTO person){
        logger.info("Creating one People!");
       var entity = parseObject(person, Person.class);

       var dto =  parseObject(repository.save(entity), PersonDTO.class);
       addHateoasLinks(dto);
       return dto;

    } public PersonDTOV2 createV2(PersonDTOV2 person){
        logger.info("Creating one People!");
       var entity = converter.convertDTOToEntity (person);

       return converter.convertEntityToDTO(repository.save(entity));

    }
    public PersonDTO update(PersonDTO person){
        logger.info("Updating one People!");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));


        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());


        var dto = parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }
    public void delete(Long id){

        logger.info("Deleting one Person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
    }

}

