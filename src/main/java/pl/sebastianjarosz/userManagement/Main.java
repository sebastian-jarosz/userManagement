package pl.sebastianjarosz.userManagement;

import pl.sebastianjarosz.userManagement.controller.Controller;
import pl.sebastianjarosz.userManagement.model.Model;
import pl.sebastianjarosz.userManagement.view.View;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::runApp);
    }

    public static void runApp() {
        //Create a model - Data representation
        Model model = new Model();

        //Create view - View for Data - Need to have Model
        View view = new View(model);

        //Business Logic - Need to have Model and View - Sending commands to model and view
        //Controller will be observer/listener
        Controller controller = new Controller(view, model);

        model.setPeopleChangedListener(view);

        view.setAppListener(controller);
        view.setCreateUserListener(controller);
        view.setSaveUserListener(controller);
        view.setDeleteUserListener(controller);
    }

}
