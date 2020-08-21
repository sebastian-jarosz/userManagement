package pl.sebastianjarosz.userManagement.model.factory;

import pl.sebastianjarosz.userManagement.model.dao.LogDAO;
import pl.sebastianjarosz.userManagement.model.dao.PersonDAO;

public abstract class DAOFactory {

    public static final int H2 = 0;
    public static final int ORACLE = 1;

    public abstract PersonDAO getPersonDAO();

    public abstract LogDAO getLogDAO();

    //This static method will return proper DAOFactory regarding to DB type
    public static DAOFactory getDAOFactory(int type)  {
        switch(type) {
            case H2:
                return new DAOH2DBFactory();
            case ORACLE:
                return new DAOORACLEFactory();
            default:
                return null;
        }
    }

}
