package lk.d24hostel.bo;

import lk.d24hostel.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes {
        ROOM,STUDENT,RESERVATION,RESERVATION_DETAIL,USER
    }
    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case ROOM:
                return new RoomBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case RESERVATION:
                return new ReservationBOImpl();
            case RESERVATION_DETAIL:
                return new ReserveDetailBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
