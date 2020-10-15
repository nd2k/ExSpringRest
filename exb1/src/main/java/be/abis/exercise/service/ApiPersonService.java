package be.abis.exercise.service;

import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.model.ChangePasswordRequest;
import be.abis.exercise.model.LoginRequest;
import be.abis.exercise.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class ApiPersonService implements PersonService {

    @Autowired
    RestTemplate restTemplate;

    private String baseUrl = "http://localhost:8085/exercise/api/persons";

    @Override
    public Person findPersonById(int id) {
        return restTemplate.getForObject(baseUrl + "/" + id, Person.class);
    }

    @Override
    public Person[] findAllPerson() {
//        PersonList response = restTemplate.getForObject(baseUrl, PersonList.class);
//        System.out.println(response);
//        ArrayList<Person> persons = response.getListOfPersons();
//        return persons;
        ResponseEntity<Person[]> responseEntity = restTemplate.getForEntity(baseUrl, Person[].class);
        Person[] persons = responseEntity.getBody();
        return persons;
    }

    @Override
    public Person findPerson(LoginRequest loginRequest) {
        HttpEntity<Object> request = new HttpEntity<>(loginRequest);
        return restTemplate.postForObject(baseUrl + "/login", request, Person.class);
    }

    @Override
    public void addPerson(Person p) throws IOException {
        HttpEntity<Object> request = new HttpEntity<>(p);
        restTemplate.postForObject(baseUrl, request, void.class);
    }

    @Override
    public void deletePerson(int id) throws PersonCanNotBeDeletedException {
        restTemplate.delete(baseUrl + "/" + id);
    }

    @Override
    public void changePassword(int id, ChangePasswordRequest changePasswordRequest) throws IOException {
        HttpEntity<Object> request = new HttpEntity<>(changePasswordRequest);
        restTemplate.put(baseUrl + "/" + id + "/password", request, void.class);
    }
}
