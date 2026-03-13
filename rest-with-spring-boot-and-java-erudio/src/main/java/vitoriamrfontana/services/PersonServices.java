package vitoriamrfontana.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vitoriamrfontana.data.dto.v1.PersonDTO;
import vitoriamrfontana.data.dto.v2.PersonDTOV2;
import vitoriamrfontana.exception.ResourceNotFoundException;
import static vitoriamrfontana.mapper.ObjectMapper.parseListObjects;
import static vitoriamrfontana.mapper.ObjectMapper.parseObject;

import vitoriamrfontana.mapper.custom.PersonMapper;
import vitoriamrfontana.model.Person;
import vitoriamrfontana.repository.PersonRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;



    public  List<PersonDTO> findAll(){
        logger.info("Finding all People!");

       return parseListObjects(repository.findAll(), PersonDTO.class);

    }


    public PersonDTO findById(Long id){
        logger.info("Find one Person!");

       var entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
       return parseObject(entity, PersonDTO.class);
    }
    public PersonDTO create(PersonDTO person){
        logger.info("Creating one People!");
       var entity = parseObject(person, Person.class);

       return parseObject(repository.save(entity), PersonDTO.class);

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


        return parseObject(repository.save(entity), PersonDTO.class);
    }
    public void delete(Long id){

        logger.info("Deleting one Person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

}

