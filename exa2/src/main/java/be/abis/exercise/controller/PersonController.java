package be.abis.exercise.controller;

import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.model.ChangePasswordRequest;
import be.abis.exercise.model.LoginRequest;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("persons")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") int id) {
        return personService.findPerson(id);
    }

    @GetMapping()
    public ArrayList<Person> getAllPerson() {
        return personService.getAllPersons();
    }

    @PostMapping("/login")
    public Person login(@RequestBody LoginRequest loginRequest) {
        return personService.findPerson(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @PostMapping("/person")
    public void addNewPerson(@RequestBody Person person) throws IOException {
        personService.addPerson(person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") int id) throws PersonCanNotBeDeletedException {
        personService.deletePerson(id);
    }

    @PutMapping("/{id}")
    public void changePasswordPerson(@PathVariable("id") int id, @RequestBody ChangePasswordRequest changePasswordRequest) throws IOException {
        Person retrivedPerson = personService.findPerson(id);
        personService.changePassword(retrivedPerson, changePasswordRequest.getNewPassword());
    }
}
