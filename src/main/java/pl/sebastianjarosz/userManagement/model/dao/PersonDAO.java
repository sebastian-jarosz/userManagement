package pl.sebastianjarosz.userManagement.model.dao;

import pl.sebastianjarosz.userManagement.model.bean.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonDAO {
    //Add person to DB
    void addPerson(Person person) throws SQLException;

    //Get person by id in DB
    Person getPerson(int id) throws SQLException;

    //Get all persons from DB - should be created only in case of small amount of data
    List<Person> getPeople() throws SQLException;

    //Update person in DB
    int updatePerson(Person person) throws SQLException;

    //Delete person by id in DB
    int deletePerson(int id) throws SQLException;

    //Delete all persons in DB
    int deletePeople() throws SQLException;
}
