package com.example.demo.service.endpoint;

import com.example.demo.operation.AttendOperation_repo;
import com.example.demo.operation.AttendOperation_repo_Impl;
import com.example.demo.operation.StudentOperation_repo;
import com.example.demo.operation.StudentOperation_repo_Impl;
import com.example.demo.types.Attend;
import com.example.demo.types.Student;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService()
public class StudentService {

    static StudentOperation_repo repo;

    public StudentService(){
        repo = new StudentOperation_repo_Impl();
    }
    public StudentService(StudentOperation_repo ao_r) {
        repo = ao_r;
    }
    @WebMethod()
    public List<Attend> getAllAttend()  {
        return repo.getListOfAttend();
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
