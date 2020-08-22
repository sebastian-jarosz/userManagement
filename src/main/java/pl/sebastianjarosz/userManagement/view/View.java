package pl.sebastianjarosz.userManagement.view;

import pl.sebastianjarosz.userManagement.model.Model;
import pl.sebastianjarosz.userManagement.model.bean.Person;
import pl.sebastianjarosz.userManagement.view.event.CreateUserEvent;
import pl.sebastianjarosz.userManagement.view.listener.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.List;

//Using JFrame for course purposes
public class View extends JFrame implements ActionListener, PeopleChangedListener {

    private Model model;

    //Fields for UI
    private JTextField nameField;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;
    private JButton createUserButton;
    private DefaultListModel<Person> userListModel;
    private JList<Person> userList;

    private AppListener appListener;
    private CreateUserListener createUserListener;
    private SaveUserListener saveUserListener;
    private DeleteUserListener deleteUserListener;

    public View(Model model) {
        super("User Management System");

        this.model = model;

        //Swing part of UI
        //Button
        nameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        repeatPasswordField = new JPasswordField(10);
        createUserButton = new JButton("Create User");
        userListModel = new DefaultListModel<>();
        userList = new JList<>(userListModel);

        //Window

        //List of users
        int margin = 15;
        Border outBorder = BorderFactory.createEmptyBorder(margin, margin, margin, margin);
        Border innerBorder = BorderFactory.createEtchedBorder();
        userList.setBorder(BorderFactory.createCompoundBorder(outBorder, innerBorder));

        setLayout(new GridBagLayout()); //Layout manager

        GridBagConstraints gBC = new GridBagConstraints();
        //To specify where label should go
        gBC.anchor = GridBagConstraints.LAST_LINE_END;
        gBC.gridx = 1;
        gBC.gridy = 1;
        gBC.weightx = 1;
        gBC.weighty = 1;
        gBC.insets = new Insets(100, 0, 0, 10);
        gBC.fill = GridBagConstraints.NONE;

        //Add Label for field
        add(new JLabel("Name: "), gBC);

        //To specify where text field should go
        gBC.anchor = GridBagConstraints.LAST_LINE_START;
        gBC.gridx = 2;
        gBC.gridy = 1;
        gBC.weightx = 1;
        gBC.weighty = 1;
        gBC.insets = new Insets(100, 0, 0, 0);
        gBC.fill = GridBagConstraints.NONE;

        //Add field - Label in code above
        add(nameField, gBC);

        //To specify where label should go
        gBC.anchor = GridBagConstraints.LINE_END;
        gBC.gridx = 1;
        gBC.gridy = 2;
        gBC.weightx = 1;
        gBC.weighty = 1;
        gBC.insets = new Insets(0, 0, 0, 10);
        gBC.fill = GridBagConstraints.NONE;

        //Add Label for field
        add(new JLabel("Password: "), gBC);

        //To specify where text field should go
        gBC.anchor = GridBagConstraints.LINE_START;
        gBC.gridx = 2;
        gBC.gridy = 2;
        gBC.weightx = 1;
        gBC.weighty = 1;
        gBC.insets = new Insets(0, 0, 0, 0);
        gBC.fill = GridBagConstraints.NONE;

        //Add field - Label in code above
        add(passwordField, gBC);

        gBC.anchor = GridBagConstraints.LINE_END;
        gBC.gridx = 1;
        gBC.gridy = 3;
        gBC.weightx = 1;
        gBC.weighty = 1;
        gBC.insets = new Insets(0, 0, 0, 10);
        gBC.fill = GridBagConstraints.NONE;

        add(new JLabel("Repeat password: "), gBC);

        gBC.anchor = GridBagConstraints.LINE_START;
        gBC.gridx = 2;
        gBC.gridy = 3;
        gBC.weightx = 1;
        gBC.weighty = 1;
        gBC.insets = new Insets(0, 0, 0, 0);
        gBC.fill = GridBagConstraints.NONE;

        add(repeatPasswordField, gBC);

        //To specify where button should go
        gBC.anchor = GridBagConstraints.FIRST_LINE_START;
        gBC.gridx = 2;
        gBC.gridy = 4;
        gBC.weightx = 1;
        gBC.weighty = 100;
        gBC.fill = GridBagConstraints.NONE;

        //Add button
        add(createUserButton, gBC);

        gBC.anchor = GridBagConstraints.FIRST_LINE_START;
        gBC.gridx = 1;
        gBC.gridy = 5;
        gBC.weightx = 1;
        gBC.weighty = 100;
        gBC.gridwidth = 2;
        gBC.fill = GridBagConstraints.HORIZONTAL;

        add(new JScrollPane(userList), gBC);

        createUserButton.addActionListener(this);

        //Examples - usage of SINGLETON object methods
        addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                fireOpenEvent();
            }

            public void windowClosing(WindowEvent e) {
                fireCloseEvent();
            }
        });

        JMenuBar menu = createMenu();
        setJMenuBar(menu);

        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setAppListener(AppListener appListener) {
        this.appListener = appListener;
    }

    public void setCreateUserListener(CreateUserListener loginListerner) {
        this.createUserListener = loginListerner;
    }

    public void setSaveUserListener(SaveUserListener saveUserListener) {
        this.saveUserListener = saveUserListener;
    }

    public void setDeleteUserListener(DeleteUserListener deleteUserListener) {
        this.deleteUserListener = deleteUserListener;
    }

    private JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem deleteAllItem = new JMenuItem("Delete All");

        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
        deleteAllItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK));

        fileMenu.add(saveItem);
        fileMenu.add(deleteAllItem);
        menuBar.add(fileMenu);

        saveItem.addActionListener(e -> fireSaveEvent());
        deleteAllItem.addActionListener(e -> fireDeleteAllEvent());

        return menuBar;
    }

    public void actionPerformed(ActionEvent e) {
        String password = passwordField.getText();
        String repeatPassword = repeatPasswordField.getText();

        //Password equality validation
        if (password.equals(repeatPassword)) {
            String name = nameField.getText();
            CreateUserEvent createUserEvent = new CreateUserEvent(name, password);
            fireCreateUserEvent(createUserEvent);

            //Clear fields
            nameField.setText("");
            passwordField.setText("");
            repeatPasswordField.setText("");

        } else {
            showError("Passwords do not match.");
        }
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message,
                "Error", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void onPeopleChange() {
        userListModel.clear();

        List<Person> people = model.getPeople();
        people.sort(Comparator.comparing(Person::getId));

        for (Person person : people) {
            userListModel.addElement(person);
        }
    }

    private void fireOpenEvent() {
        if(appListener != null) {
            appListener.onOpen();
        }
    }

    private void fireCloseEvent() {
        if(appListener != null) {
            appListener.onClose();
        }
    }

    private void fireCreateUserEvent(CreateUserEvent createUserEvent) {
        if(createUserListener != null) {
            createUserListener.onUserCreated(createUserEvent);
        }
    }

    private void fireSaveEvent() {
        if(saveUserListener != null) {
            saveUserListener.onPersonSave();
        }
    }

    private void fireDeleteAllEvent() {
        if(deleteUserListener != null) {
            deleteUserListener.onAllPeopleDelete();
        }
    }

}
