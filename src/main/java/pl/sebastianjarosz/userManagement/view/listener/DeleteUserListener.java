package pl.sebastianjarosz.userManagement.view.listener;

import pl.sebastianjarosz.userManagement.model.bean.Person;

public interface DeleteUserListener {

    void onPersonDelete(Person person);

    void onAllPeopleDelete();

}
