package lk.d24hostel.bo.custom;

import lk.d24hostel.dto.CustomDTO;
import lk.d24hostel.dto.ReservedDTO;
import lk.d24hostel.dto.RoomDTO;
import lk.d24hostel.dto.StudentDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ReserveDetailBO extends SuperBO{

    ArrayList<CustomDTO> getAllReservationDetails() throws IOException;
    ArrayList<RoomDTO> getAllRoom() throws IOException;
    ArrayList<StudentDTO> getAllStudent() throws IOException;
    boolean updateReservation(ReservedDTO dto) throws IOException;
    List<ReservedDTO> searchReservedRoomById(String id) throws IOException;
}
