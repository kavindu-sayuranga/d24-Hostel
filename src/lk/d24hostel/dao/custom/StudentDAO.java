package lk.d24hostel.dao.custom;

import lk.d24hostel.dao.CrudDAO;
import lk.d24hostel.entity.Student;

import java.io.IOException;

public interface StudentDAO extends CrudDAO<Student,String> {

    Student getStudent(String id) throws IOException;
}
