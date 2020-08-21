package pl.sebastianjarosz.userManagement.model.bean;

import java.util.Objects;

//Example of Bean class
public class Person {

    private int id;
    private String name;
    private String password;

    public Person() {

    }

    //We dont want to manually add the ID - it should be automated
    public Person(String name, String password) {
        this.name = name;
        this.password = password;
    }

    //We dont want to manually add the ID - it should be automated
    public Person(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return id + ": " + name + ": " + password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(password, person.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}
