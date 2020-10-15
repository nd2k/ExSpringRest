package be.abis.exercise.service;

import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.model.ChangePasswordRequest;
import be.abis.exercise.model.LoginRequest;
import be.abis.exercise.model.Person;

import java.io.IOException;
import java.util.ArrayList;

public interface PersonService {

    Person findPersonById(int id);
    Person[] findAllPerson();
    Person findPerson(LoginRequest loginRequest);
    void addPerson(Person p) throws IOException;
    public void deletePerson(int id) throws PersonCanNotBeDeletedException;
    void changePassword(int id, ChangePasswordRequest changePasswordRequest) throws IOException;
}
