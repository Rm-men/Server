package com.example.demo.service.endpoint;

import com.example.demo.operation.StudentOperationImpl;
import com.example.demo.types.Attend;
import com.example.demo.types.Student;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.sql.SQLException;
import java.util.List;

@WebService()
public class StudentService {

    static StudentOperationImpl obj;

    static {
        try {
            obj = new StudentOperationImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @WebMethod()
    public List<Attend> getAllAttend() throws SQLException {
        return obj.getListOfAttend();
    }

    @WebMethod()
    public Attend setAttend(Student s, Attend a) throws SQLException {
        return obj.markAttend(s,a);
    }

    @WebMethod()
    public Student loginStudent(String code) throws SQLException {
        return  obj.loginStudent(code);
    }
}
