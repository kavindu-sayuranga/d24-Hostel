package lk.d24hostel.dto;


import lk.d24hostel.entity.Room;
import lk.d24hostel.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomDTO {

    String studentId;
    String name;
    String address;
    String telNo;
    LocalDate dob;
    String gender;

    String resId;
    LocalDate date;
    Student regStudentId;
    Room regRoomId;
    String status;

    private String roomTypeId;
    private String type;
    private double keyMoney;
    private int qty;
}
