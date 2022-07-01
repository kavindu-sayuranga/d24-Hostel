package lk.d24hostel.bo.custom.impl;

import lk.d24hostel.bo.custom.ReservationBO;
import lk.d24hostel.dao.custom.ReservationDAO;
import lk.d24hostel.dao.custom.RoomDAO;
import lk.d24hostel.dao.custom.StudentDAO;
import lk.d24hostel.dao.custom.impl.ReservationDAOImpl;
import lk.d24hostel.dao.custom.impl.RoomDAOImpl;
import lk.d24hostel.dao.custom.impl.StudentDAOImpl;
import lk.d24hostel.dto.ReservedDTO;
import lk.d24hostel.dto.RoomDTO;
import lk.d24hostel.dto.StudentDTO;
import lk.d24hostel.entity.Reserved;
import lk.d24hostel.entity.Room;
import lk.d24hostel.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {

    StudentDAO studentDAO = new StudentDAOImpl();
    RoomDAO roomDAO = new RoomDAOImpl();
    ReservationDAO reservationDAO = new ReservationDAOImpl();

    @Override
    public Room getRoom(String id) throws IOException {
        return roomDAO.getRoom(id);
    }

    @Override
    public Student getStudent(String id) throws IOException {
        return studentDAO.getStudent(id);
    }

    @Override
    public ArrayList<RoomDTO> getAllRoom() throws IOException {
        ArrayList<Room> all = roomDAO.getAll();
        ArrayList<RoomDTO> allRoom = new ArrayList<>();

        for (Room room : all) {
            allRoom.add(new RoomDTO(
                    room.getRoomTypeId(),
                    room.getType(),
                    room.getKeyMoney(),
                    room.getQty()
            ));
        }
        return allRoom;
    }

    @Override
    public ArrayList<StudentDTO> getAllStudent() throws IOException {
        ArrayList<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudent = new ArrayList<>();

        for (Student student : all) {
            allStudent.add(new StudentDTO(
                    student.getStudentId(),
                    student.getName(),
                    student.getAddress(),
                    student.getTelNo(),
                    student.getDob(),
                    student.getGender()
            ));
        }
        return allStudent;    }

    @Override
    public List<ReservedDTO> searchReservedRoomById(String id) throws IOException {
        List<Reserved> reserves = reservationDAO.searchReservedRoomById(id);

        List<ReservedDTO> reserveDTOS = new ArrayList<>();

        for (Reserved reserve : reserves) {
            reserveDTOS.add(new ReservedDTO(
                    reserve.getResId(),
                    reserve.getDate(),
                    reserve.getStudent(),
                    reserve.getRoom(),
                    reserve.getStatus()
            ));
        }
        return reserveDTOS;
    }

    @Override
    public boolean registerStudent(ReservedDTO dto) throws IOException {
        return reservationDAO.save(new Reserved(
                dto.getResId(),
                dto.getDate(),
                dto.getStudentId(),
                dto.getRoomId(),
                dto.getStatus()
        ));
    }
}
