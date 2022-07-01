package lk.d24hostel.dao.custom;

import lk.d24hostel.dao.CrudDAO;
import lk.d24hostel.entity.Reserved;

import java.io.IOException;
import java.util.List;

public interface ReservationDAO extends CrudDAO<Reserved,String> {

    List<Reserved> searchReservedRoomById (String id) throws IOException;
}
