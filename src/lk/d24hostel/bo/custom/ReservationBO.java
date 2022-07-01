package lk.d24hostel.bo.custom;

import lk.d24hostel.dto.ReservedDTO;
import lk.d24hostel.dto.RoomDTO;
import lk.d24hostel.dto.StudentDTO;
import lk.d24hostel.entity.Room;
import lk.d24hostel.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ReservationBO extends SuperBO{

    Room getRoom(String id)throws IOException;

    Student getStudent(String id)throws IOException;

    ArrayList<RoomDTO> getAllRoom()throws IOException;

    ArrayList<StudentDTO> getAllStudent()throws IOException;

    List<ReservedDTO> searchReservedRoomById(String id) throws IOException;

    boolean registerStudent(ReservedDTO dto) throws IOException;
}
