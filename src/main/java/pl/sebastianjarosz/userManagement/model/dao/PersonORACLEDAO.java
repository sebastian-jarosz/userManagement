package pl.sebastianjarosz.userManagement.model.dao;

import pl.sebastianjarosz.userManagement.model.bean.Person;

import java.sql.SQLException;
import java.util.List;

/**
 * Example class for Oracle - Not implemented, only used as DAO for Multiple DBs
 */
public class PersonORACLEDAO implements PersonDAO {

    @Override
    public void addPerson(Person person) throws SQLException {

    }

    @Override
    public Person getPerson(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Person> getPeople() throws SQLException {
        return null;
    }

    @Override
    public int updatePerson(Person person) throws SQLException {
        return 0;
    }

    @Override
    public int deletePerson(int id) throws SQLException {
        return 0;
    }

    @Override
    public int deletePeople() throws SQLException {
        return 0;
    }

}
