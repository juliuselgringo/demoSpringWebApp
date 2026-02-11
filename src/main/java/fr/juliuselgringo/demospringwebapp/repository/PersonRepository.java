package fr.juliuselgringo.demospringwebapp.repository;

import fr.juliuselgringo.demospringwebapp.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import fr.juliuselgringo.demospringwebapp.config.CustomProperty;
import org.springframework.web.client.RestTemplate;

@Repository
@Slf4j
public class PersonRepository {

    @Autowired
    private CustomProperty property;

    public Iterable<Person> getPersons(){
        String baseUrl = property.getApiUrl();
        String getPersonsURL = baseUrl + "/persons";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Person>> responseEntity = restTemplate.exchange(
                getPersonsURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        log.debug("Get Persons call {}", responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    public Person getPerson(int id){
        String baseApiUrl = property.getApiUrl();
        String getPersonURL = baseApiUrl + "/person/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Person> response = restTemplate.exchange(
                getPersonURL,
                HttpMethod.GET,
                null,
                Person.class
        );

        log.debug("Get Person call {}", response.getStatusCode());
        return response.getBody();
    }

    public Person createPerson(Person person){
        String baseApiUrl = property.getApiUrl();
        String createPersonURL = baseApiUrl + "/person";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Person> request = new HttpEntity<>(person);
        ResponseEntity<Person> response = restTemplate.exchange(
                createPersonURL,
                HttpMethod.POST,
                request,
                Person.class
        );

        log.debug("Create Person call {}", response.getStatusCode());
        return response.getBody();
    }

    public Person updatePerson(Person person){
        String baseApiUrl = property.getApiUrl();
        String updatePersonURL = baseApiUrl + "/person/" + person.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Person> request = new HttpEntity<>(person);
        ResponseEntity<Person> response = restTemplate.exchange(
                updatePersonURL,
                HttpMethod.PUT,
                request,
                Person.class
        );

        log.debug("Update Person call {}", response.getStatusCode());
        return response.getBody();
    }

    public void deletePerson(int id){
        String baseApiUrl = property.getApiUrl();
        String deletePersonURL = baseApiUrl + "/person/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deletePersonURL,
                HttpMethod.DELETE,
                null,
                Void.class
        );
        log.debug("Delete Person call {}", response.getStatusCode());
    }

}
