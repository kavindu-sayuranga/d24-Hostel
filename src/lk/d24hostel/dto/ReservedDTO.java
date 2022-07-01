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
public class ReservedDTO {

    String resId;
    LocalDate date;
    Student studentId;
    Room roomId;
    String status;

}
