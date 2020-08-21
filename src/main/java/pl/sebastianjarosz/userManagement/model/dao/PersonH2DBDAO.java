package pl.sebastianjarosz.userManagement.model.dao;

import pl.sebastianjarosz.userManagement.model.Database;
import pl.sebastianjarosz.userManagement.model.bean.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * One DAO class per person or view - Class will deal with DB
 * CRUD - Crate, Retrieve, Update, Delete methods
 */
public class PersonH2DBDAO implements PersonDAO {

    private String ID = "id";
    private String NAME = "name";
    private String PASSWORD = "password";

    //Add person to DB
    @Override
    public void addPerson(Person person) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        String insertPersonQuery = "INSERT INTO PEOPLE (name, password) values (?, ?)";

        PreparedStatement ps = connection.prepareStatement(insertPersonQuery);

        //Setting parameters to prepared statement
        ps.setString(1, person.getName());
        ps.setString(2, person.getPassword());

        //Execute statement - INSERT
        ps.executeUpdate();
        ps.close();
    }

    //Get person by id in DB
    @Override
    public Person getPerson(int id) throws SQLException {
        Person person = null;
        String personByIdQuery = "SELECT * FROM PEOPLE WHERE ID = ?";

        Connection connection = Database.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(personByIdQuery);

        ps.setInt(1, id);

        ResultSet resultSet = ps.executeQuery();

        if(resultSet.next()) {
            String name = resultSet.getString(NAME);
            String password = resultSet.getString(PASSWORD);
            person = new Person(id, name, password);
        }

        //Clean env
        resultSet.close();
        ps.close();

        return person;
    }

    //Get all persons from DB - should be created only in case of small amount of data
    @Override
    public List<Person> getPeople() throws SQLException {
        List<Person> personList = new ArrayList<>();
        String allPersonsQuery = "SELECT * FROM PEOPLE ORDER BY id";

        Connection connection = Database.getInstance().getConnection();
        Statement selectStatement = connection.createStatement();

        ResultSet resultSet = selectStatement.executeQuery(allPersonsQuery);

        while (resultSet.next()) {
            int id = resultSet.getInt(ID);
            String name = resultSet.getString(NAME);
            String password = resultSet.getString(PASSWORD);

            Person person = new Person(id, name, password);
            personList.add(person);
        }

        //Clean env
        resultSet.close();
        selectStatement.close();

        return personList;
    }

    //Update person in DB
    @Override
    public int updatePerson(Person person) throws SQLException {
        String updatePersonQuery = "UPDATE PEOPLE SET name=?, password=? WHERE id=?";

        Connection connection = Database.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(updatePersonQuery);

        ps.setString(1, person.getName());
        ps.setString(2, person.getPassword());
        ps.setInt(3, person.getId());

        int updated = ps.executeUpdate();

        ps.close();

        return updated;
    }

    //Delete person by id in DB
    @Override
    public int deletePerson(int id) throws SQLException {
        String deletePersonByIdQuery = "DELETE FROM PEOPLE WHERE id=?";

        Connection connection = Database.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(deletePersonByIdQuery);

        ps.setInt(1, id);

        int deleted = ps.executeUpdate();

        ps.close();

        return deleted;
    }

    //Delete all persons in DB
    @Override
    public int deletePeople() throws SQLException {
        String deletePeopleQuery = "DELETE FROM PEOPLE";

        Connection connection = Database.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(deletePeopleQuery);

        int deleted = ps.executeUpdate();

        ps.close();

        return deleted;
    }

}
