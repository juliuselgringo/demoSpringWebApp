package fr.juliuselgringo.demospringwebapp.service;

import fr.juliuselgringo.demospringwebapp.model.Person;
import fr.juliuselgringo.demospringwebapp.repository.PersonRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person getPerson(Integer id) {
        return personRepository.getPerson(id);
    }

    public Iterable<Person> getAllPersons() {
        return personRepository.getPersons();
    }

    public Person savePerson(Person person) {
        Person saved;

        person.setLastName(person.getLastName().toUpperCase());

        if(person.getId() == null) {
            saved = personRepository.createPerson(person);
        }else{
            saved = personRepository.updatePerson(person);
        }
        return saved;
    }

    public void deletePerson(Integer id) {
        personRepository.deletePerson(id);
    }



}
