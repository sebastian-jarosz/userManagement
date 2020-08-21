package pl.sebastianjarosz.userManagement.model.factory;

import pl.sebastianjarosz.userManagement.model.dao.LogDAO;
import pl.sebastianjarosz.userManagement.model.dao.LogORACLEDAO;
import pl.sebastianjarosz.userManagement.model.dao.PersonDAO;
import pl.sebastianjarosz.userManagement.model.dao.PersonORACLEDAO;

public class DAOORACLEFactory extends DAOFactory {

    @Override
    public PersonDAO getPersonDAO() {
        return new PersonORACLEDAO();
    }

    @Override
    public LogDAO getLogDAO() {
        return new LogORACLEDAO();
    }

}
