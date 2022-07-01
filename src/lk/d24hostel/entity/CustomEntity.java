package lk.d24hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomEntity {
    String studentId;
    String name;
    String address;
    String telNo;
    LocalDate dob;
    String gender;

    private String roomTypeId;
    private String type;
    private double keyMoney;
    private int qty;

    String resId;
    LocalDate date;
    Student regStudentId;
    Room regRoomId;
    String status;
}
