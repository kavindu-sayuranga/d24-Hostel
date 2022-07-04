package lk.d24hostel.dao;

import lk.d24hostel.dao.custom.impl.ReservationDAOImpl;
import lk.d24hostel.dao.custom.impl.RoomDAOImpl;
import lk.d24hostel.dao.custom.impl.StudentDAOImpl;
import lk.d24hostel.dao.custom.impl.UserDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    //singleton
    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes {
        ROOM,STUDENT,RESERVATION,USER
    }
    public SuperDAO getDAO(DAOFactory.DAOTypes types) {
        switch (types) {
            case ROOM:
                return new RoomDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }


}
