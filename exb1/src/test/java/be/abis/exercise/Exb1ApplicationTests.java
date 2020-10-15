package be.abis.exercise;

import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.model.*;
import be.abis.exercise.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Exb1ApplicationTests {

	@Autowired
	PersonService personService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getFirstNameIsJohn() {
		String expected = "John";
		String firstNamePersonId1 = personService.findPersonById(1).getFirstName();
		assertEquals(firstNamePersonId1, expected);
	}

	@Test
	public void AddNewPerson() throws IOException {
		Person testPerson = new Person();
		Company testCompany = new Company();
		Address testAddress = new Address();
		testPerson.setPersonId(10);
		testPerson.setFirstName("Test");
		testPerson.setLastName("Essai");
		testPerson.setEmailAddress("test@gmail.com");
		testPerson.setPassword("abc123");
		testPerson.setAge(25);
		testPerson.setLanguage("FR");
		testCompany.setName("BNP");
		testCompany.setTelephoneNumber("025465623");
		testCompany.setVatNr("BE2564563");
		testAddress.setNr(5);
		testAddress.setStreet("rue des essais");
		testAddress.setTown("Bruxelles");
		testAddress.setZipcode("1000");
		testPerson.setCompany(testCompany);
		testCompany.setAddress(testAddress);

		personService.addPerson(testPerson);

		String expectedEmail = personService.findPersonById(10).getEmailAddress();

		assertEquals(expectedEmail, testPerson.getEmailAddress());
	}

	@Test
	public void getPersonViaEmailAndPassword() {

		int expectedId = 1;
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail("jdoe@abis.be");
		loginRequest.setPassword("abc123");
		int retrievedPersonId = personService.findPerson(loginRequest).getPersonId();
		assertEquals(expectedId, retrievedPersonId);
	}

	@Test
	public void deletePersonById() throws PersonCanNotBeDeletedException {
		int testId = 10;
		personService.deletePerson(testId);
		Person deletePerson = personService.findPersonById(testId);
		assertThat(deletePerson, is(nullValue()));
	}

	@Test
	public void changePasswordPerson() throws IOException {
		int testid = 1;
		String expectedPassword = "abc123";
		ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
		changePasswordRequest.setNewPassword(expectedPassword);
		personService.changePassword(testid, changePasswordRequest);
		String retrievedPassword = personService.findPersonById(testid).getPassword();
		assertEquals(expectedPassword, retrievedPassword);
	}

	@Test
	public void getListOfPerson() {
		int lengthOfList = 4;
		ArrayList<Person> listOfPersons = personService.findAllPerson();
		int sizeOfPersonListRetrieved = listOfPersons.size();
		assertEquals(lengthOfList, sizeOfPersonListRetrieved);
	}

}
