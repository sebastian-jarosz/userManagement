package pl.sebastianjarosz.userManagement.model.factory;

import pl.sebastianjarosz.userManagement.model.dao.LogDAO;
import pl.sebastianjarosz.userManagement.model.dao.LogH2DBDAO;
import pl.sebastianjarosz.userManagement.model.dao.PersonDAO;
import pl.sebastianjarosz.userManagement.model.dao.PersonH2DBDAO;

public class DAOH2DBFactory extends DAOFactory {

    public PersonDAO getPersonDAO() {
        return new PersonH2DBDAO();
    }

    public LogDAO getLogDAO() {
        return new LogH2DBDAO();
    }

}
