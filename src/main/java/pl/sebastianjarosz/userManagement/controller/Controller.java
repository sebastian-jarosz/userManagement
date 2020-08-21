package pl.sebastianjarosz.userManagement.controller;

import pl.sebastianjarosz.userManagement.model.Database;
import pl.sebastianjarosz.userManagement.model.Model;
import pl.sebastianjarosz.userManagement.model.bean.Person;
import pl.sebastianjarosz.userManagement.view.View;
import pl.sebastianjarosz.userManagement.view.event.CreateUserEvent;
import pl.sebastianjarosz.userManagement.view.listener.AppListener;
import pl.sebastianjarosz.userManagement.view.listener.CreateUserListener;
import pl.sebastianjarosz.userManagement.view.listener.SaveUserListener;

public class Controller implements AppListener, CreateUserListener, SaveUserListener {

    private View view;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onUserCreated(CreateUserEvent createUserEvent) {
        model.addPerson(new Person(createUserEvent.getName(), createUserEvent.getPassword()));
    }

    @Override
    public void onOpen() {
        try {
            Database.getInstance().connectDatabase();
        } catch (Exception exception) {
            System.out.println("Error - Cannot connect to database");
        }

        try {
            model.load();
        } catch (Exception e) {
            System.out.println("Error - Cannot get data from database");
        }
    }

    @Override
    public void onClose() {
        Database.getInstance().disconnectDatabase();
    }

    @Override
    public void onPersonSave() {
        try {
            model.save();
        } catch (Exception e) {
            view.showError("Error saving to database.");
        }
    }
}
