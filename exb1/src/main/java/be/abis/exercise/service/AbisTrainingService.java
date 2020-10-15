package be.abis.exercise.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import be.abis.exercise.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.abis.exercise.exception.EnrollException;
import be.abis.exercise.exception.PersonCanNotBeDeletedException;

@Service
public class AbisTrainingService implements TrainingService {
    
	@Autowired
	private CourseService courseService;

	@Autowired
	PersonService personService;
	
	@Override
	public CourseService getCourseService() {
		return courseService;
	}


	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	@Override
	public ArrayList<Person> getAllPersons() {
		return personService.findAllPerson();
	}

	@Override
	public Person findPerson(int id) {
		return personService.findPersonById(id);
	}

	@Override
	public Person findPerson(String emailAddress, String password) {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setPassword(password);
		loginRequest.setEmail(emailAddress);
		return personService.findPerson(loginRequest);
	}

	@Override
	public void addPerson(Person p) throws IOException {
		personService.addPerson(p);
	}

	@Override
	public void deletePerson(int id) throws PersonCanNotBeDeletedException {
		personService.deletePerson(id);
	}

	@Override
	public void changePassword(Person p, String newPswd) throws IOException {
		ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
		changePasswordRequest.setNewPassword(newPswd);
		personService.changePassword(p.getPersonId(), changePasswordRequest);
	}

	@Override
	public List<Course> showFollowedCourses(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enrollForSession(Person person, Course course, LocalDate date) throws EnrollException {
		// TODO Auto-generated method stub

	}






}
