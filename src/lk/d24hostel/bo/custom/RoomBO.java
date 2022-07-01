package lk.d24hostel.bo.custom;

import lk.d24hostel.dto.RoomDTO;
import lk.d24hostel.entity.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface RoomBO extends SuperBO{

    ArrayList<RoomDTO> getAllRoom()throws IOException;

    boolean saveRoom(RoomDTO dto)throws IOException;

    boolean updateRoom(RoomDTO dto)throws IOException;

    boolean deleteRoom(String id)throws IOException;

    String generateRoomId();

    List<Room> getRoomDataFromType(String type) throws IOException;

    Room getRoom(String id) throws IOException;
}
