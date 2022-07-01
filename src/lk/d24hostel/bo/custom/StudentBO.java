package lk.d24hostel.bo.custom;

import lk.d24hostel.dto.StudentDTO;

import java.io.IOException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO{

    ArrayList<StudentDTO> getAllStudent()throws IOException;

    boolean saveStudent(StudentDTO dto)throws IOException;

    boolean updateStudent(StudentDTO dto)throws IOException;

    boolean deleteStudent(String id)throws IOException;

    String generateStudentId();
}
