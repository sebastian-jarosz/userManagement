package pl.sebastianjarosz.userManagement.view.listener;

import pl.sebastianjarosz.userManagement.view.event.CreateUserEvent;

public interface CreateUserListener {

    void onUserCreated(CreateUserEvent createUserEvent);

}
