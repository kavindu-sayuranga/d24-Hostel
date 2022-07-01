package lk.d24hostel.bo.custom.impl;

import lk.d24hostel.bo.custom.ReserveDetailBO;
import lk.d24hostel.dao.custom.ReservationDAO;
import lk.d24hostel.dao.custom.RoomDAO;
import lk.d24hostel.dao.custom.StudentDAO;
import lk.d24hostel.dao.custom.impl.ReservationDAOImpl;
import lk.d24hostel.dao.custom.impl.RoomDAOImpl;
import lk.d24hostel.dao.custom.impl.StudentDAOImpl;
import lk.d24hostel.dto.CustomDTO;
import lk.d24hostel.dto.ReservedDTO;
import lk.d24hostel.dto.RoomDTO;
import lk.d24hostel.dto.StudentDTO;
import lk.d24hostel.entity.Reserved;
import lk.d24hostel.entity.Room;
import lk.d24hostel.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReserveDetailBOImpl implements ReserveDetailBO {

    StudentDAO studentDAO = new StudentDAOImpl();
    RoomDAO roomDAO = new RoomDAOImpl();
    ReservationDAO reservationDAO = new ReservationDAOImpl();

    @Override
    public ArrayList<CustomDTO> getAllReservationDetails() throws IOException {
        ArrayList<Reserved> all = reservationDAO.getAll();

        ArrayList<CustomDTO> allList = new ArrayList<>();

        for (Reserved res : all) {
            allList.add(new CustomDTO(
                    res.getStudent().getStudentId(),
                    res.getStudent().getName(),
                    res.getStudent().getAddress(),
                    res.getStudent().getTelNo(),
                    res.getStudent().getDob(),
                    res.getStudent().getGender(),
                    res.getResId(),
                    res.getDate(),
                    res.getStudent(),
                    res.getRoom(),
                    res.getStatus(),
                    res.getRoom().getRoomTypeId(),
                    res.getRoom().getType(),
                    res.getRoom().getKeyMoney(),
                    res.getRoom().getQty()

            ));
        }
        return allList;
    }

    @Override
    public ArrayList<RoomDTO> getAllRoom() throws IOException {
        ArrayList<Room> all = roomDAO.getAll();
        ArrayList<RoomDTO> allRooms = new ArrayList<>();

        for (Room room : all) {
            allRooms.add(new RoomDTO(
                    room.getRoomTypeId(),
                    room.getType(),
                    room.getKeyMoney(),
                    room.getQty()
            ));

        }
        return allRooms;
    }

    @Override
    public ArrayList<StudentDTO> getAllStudent() throws IOException {
        ArrayList<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudents = new ArrayList<>();

        for (Student student : all) {
            allStudents.add(new StudentDTO(
                    student.getStudentId(),
                    student.getName(),
                    student.getAddress(),
                    student.getTelNo(),
                    student.getDob(),
                    student.getGender()
            ));
        }
        return allStudents;
    }

    @Override
    public boolean updateReservation(ReservedDTO dto) throws IOException {
        return reservationDAO.update(new Reserved(
                dto.getResId(),
                dto.getDate(),
                dto.getStudentId(),
                dto.getRoomId(),
                dto.getStatus()
        ));
    }

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
}
