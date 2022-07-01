package lk.d24hostel.bo.custom.impl;

import lk.d24hostel.bo.custom.StudentBO;
import lk.d24hostel.dao.custom.StudentDAO;
import lk.d24hostel.dao.custom.impl.StudentDAOImpl;
import lk.d24hostel.dto.StudentDTO;
import lk.d24hostel.entity.Student;

import java.io.IOException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public ArrayList<StudentDTO> getAllStudent() throws IOException {
        ArrayList<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudent = new ArrayList<>();

        for (Student student : all) {
            allStudent.add(new StudentDTO(student.getStudentId(),student.getName(),student.getAddress(),student.getTelNo(),student.getDob(),student.getGender()));
        }
        return allStudent;
    }

    @Override
    public boolean saveStudent(StudentDTO dto) throws IOException {
        return studentDAO.save(new Student(
                dto.getStudentId(),
                dto.getName(),
                dto.getAddress(),
                dto.getTelNo(),
                dto.getDob(),
                dto.getGender()
        ));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws IOException {
        return studentDAO.update(new Student(
                dto.getStudentId(),
                dto.getName(),
                dto.getAddress(),
                dto.getTelNo(),
                dto.getDob(),
                dto.getGender()
        ));
    }

    @Override
    public boolean deleteStudent(String id) throws IOException {
        return studentDAO.delete(id);

    }

    @Override
    public String generateStudentId() {
        return null;
    }
}
