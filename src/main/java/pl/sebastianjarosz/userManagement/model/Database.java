package pl.sebastianjarosz.userManagement.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//Singleton example
public class Database {

    private String DB_DRIVER_CLASS = "org.h2.Driver";
    private String DB_URL = "jdbc:h2:~/test";
    private String DB_USER = "sa";
    private String DB_PASS = "";

    private String CREATE_TABLE_PERSON_QUERY =  "CREATE TABLE PEOPLE " +
            "(id INTEGER auto_increment, " +
            " name VARCHAR(45), " +
            " password VARCHAR(45), " +
            " PRIMARY KEY (id))";

    private String CREATE_TABLE_LOG_QUERY = "CREATE TABLE LOG " +
            "(id INTEGER auto_increment, " +
            " time TIMESTAMP, " +
            " message VARCHAR(255), " +
            " PRIMARY KEY (id))";

    //Declaration and initialization of singleton static variable which will be used
    private static Database instance = new Database();

    private Connection connection;

    //Creation of private constructor prevents from using it from outside of class
    private Database(){

    }

    //Getter must be static - then we can use it from class, not from object
    public static Database getInstance() {
        return instance;
    }

    //Old way - LAZY INSTANTIATION
    //Instantiation will not happen until the last possible moment
    //it will be instantiated only when somebody will invoke the getter
    private static Database instanceOld;

    public static Database getInstanceOld() {
        if(instanceOld == null) {
            instanceOld = new Database();
        }

        return instanceOld;
    }

    public Connection getConnection() {
        return connection;
    }

    //Examples methods of object
    //JDBC - To connect the database
    public void connectDatabase() throws Exception {
        if(connection != null) {
            return;
        }

        //Check if there is driver package
        try {
            //Check if Driver class exists
            Class.forName(DB_DRIVER_CLASS);
            //Specify the JDBC URL - H2 DB
            connection = DriverManager.getConnection (DB_URL, DB_USER,DB_PASS);
            System.out.println("Connected!");

            try {
                //Execute query
                Statement statement = connection.createStatement();
                statement.executeUpdate(CREATE_TABLE_PERSON_QUERY);
                System.out.println("Table PEOPLE created!");
                //Clean env - close statement
                statement.close();
            } catch (SQLException e) {
                System.out.println("Table PEOPLE already exists");
            }

            try {
                //Execute query
                Statement statement = connection.createStatement();
                statement.executeUpdate(CREATE_TABLE_LOG_QUERY);
                System.out.println("Table LOG created!");
                //Clean env - close statement
                statement.close();
            } catch (SQLException e) {
                System.out.println("Table LOG already exists");
            }

        } catch (ClassNotFoundException e) {
            throw new Exception("Driver not found");
        }
    }

    public void disconnectDatabase() {
        //Close connection
        if(connection != null){
            try {
                connection.close();
                connection = null;
                System.out.println("Disconnected!");
            } catch (SQLException e) {
                System.out.println("Can't close connection!");
            }
        }

    }

}
