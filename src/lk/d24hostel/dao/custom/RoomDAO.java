package lk.d24hostel.dao.custom;

import lk.d24hostel.dao.CrudDAO;
import lk.d24hostel.entity.Room;

import java.io.IOException;
import java.util.List;

public interface RoomDAO extends CrudDAO<Room,String> {

    List<Room> getRoomDataFromType(String type) throws IOException;
    Room getRoom(String id) throws IOException;
}
