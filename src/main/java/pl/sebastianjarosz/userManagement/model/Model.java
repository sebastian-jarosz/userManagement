package pl.sebastianjarosz.userManagement.model;

import pl.sebastianjarosz.userManagement.model.bean.Person;
import pl.sebastianjarosz.userManagement.model.dao.PersonDAO;
import pl.sebastianjarosz.userManagement.model.factory.DAOFactory;
import pl.sebastianjarosz.userManagement.view.listener.PeopleChangedListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Model {

    private HashSet<Person> peopleSet = new HashSet<>();

    private PeopleChangedListener peopleChangedListener;

    public List<Person> getPeople() {
        return new ArrayList<>(peopleSet);
    }

    public void setPeopleChangedListener(PeopleChangedListener peopleChangedListener) {
        this.peopleChangedListener = peopleChangedListener;
    }

    public void addPerson(Person person) {
        peopleSet.add(person);
        fireDataChanged();
    }

    public void deletePerson(Person person) {
        peopleSet.remove(person);
        fireDataChanged();
    }

    public void deleteAllPeople() {
        peopleSet.clear();
        fireDataChanged();
    }

    public void save() throws SQLException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.H2);
        PersonDAO personDAO = factory.getPersonDAO();

        //All records from DB
        List<Person> people = personDAO.getPeople();

        for (Person person : peopleSet) {
            if (person.getId() != 0) {
                // If the person has an ID, the record must
                // already exist in the database, because we
                // get the IDs from the database autoincrement
                // ID column.
                personDAO.updatePerson(person);
            } else {
                personDAO.addPerson(new Person(person.getName(), person
                        .getPassword()));
            }
            people.remove(person);
        }

        //Remove records removed on screen
        for (Person person : people) {
            personDAO.deletePerson(person.getId());
        }

        // Load to get the latest IDs for any new people added.
        load();
    }

    public void load() throws SQLException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.H2);
        PersonDAO personDAO = factory.getPersonDAO();

        peopleSet.clear();
        peopleSet.addAll(personDAO.getPeople());

        fireDataChanged();
    }

    public void fireDataChanged() {
        if(peopleChangedListener != null) {
            peopleChangedListener.onPeopleChange();
        }
    }

}
