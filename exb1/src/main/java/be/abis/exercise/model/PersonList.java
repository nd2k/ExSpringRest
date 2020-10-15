package be.abis.exercise.model;

import java.util.ArrayList;

public class PersonList {

    private ArrayList<Person> listOfPersons;

    public PersonList() {
        listOfPersons = new ArrayList<>();
    }

    public ArrayList<Person> getListOfPersons() {
        return listOfPersons;
    }

    public void setListOfPersons(ArrayList<Person> listOfPersons) {
        this.listOfPersons = listOfPersons;
    }
}
