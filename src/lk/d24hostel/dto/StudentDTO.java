package lk.d24hostel.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    String studentId;
    String name;
    String address;
    String telNo;
    LocalDate dob;
    String gender;
}
