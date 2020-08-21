package pl.sebastianjarosz.userManagement.model.dao;

import pl.sebastianjarosz.userManagement.model.bean.Log;

import java.util.List;

/**
 * Example class for Oracle - Not implemented, only used as DAO for Multiple DBs
 */
public class LogORACLEDAO implements LogDAO {

    @Override
    public void addEntry(String message) {

    }

    @Override
    public List<Log> getEntries(int number) {
        return null;
    }

}
