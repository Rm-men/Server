package com.example.demo.service.endpoint;

import com.example.demo.operation.StudentOperation_repo;
import com.example.demo.operation.StudentOperation_repo_Impl;
import com.example.demo.types.Attend;
import com.example.demo.types.Student;
import com.example.demo.utils.JDBCUtils;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.sql.SQLException;
import java.util.List;

@WebService()
public class StudentService {

    static StudentOperation_repo repo;

    public StudentService() throws SQLException {
        repo = new StudentOperation_repo_Impl();
        repo.setConn(JDBCUtils.getConnectJDBC());

    }
    public StudentService(StudentOperation_repo ao_r) throws SQLException {
        repo = ao_r;
        repo.setConn(JDBCUtils.getConnectJDBC());

    }
    @WebMethod()
    public List<Attend> getAllAttendForStudent(Student student)  {
        return repo.getListOfAttend(student);
    }

    @WebMethod()
    public Attend setAttend(Student s, Attend a)  {
        return repo.markAttend(s,a);
    }

    @WebMethod()
    public Student loginStudent(String code)  {
        return  repo.loginStudent(code);
    }
}
