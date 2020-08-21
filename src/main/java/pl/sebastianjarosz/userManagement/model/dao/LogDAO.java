package pl.sebastianjarosz.userManagement.model.dao;

import pl.sebastianjarosz.userManagement.model.bean.Log;

import java.util.List;

public interface LogDAO {
    void addEntry(String message);

    List<Log> getEntries(int number);
}
