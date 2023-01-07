package com.example.demo.service.endpoint;

import com.example.demo.operation.StudentOperationImpl;
import com.example.demo.types.Attend;
import com.example.demo.types.Student;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService()
public class StudentService {

    static StudentOperationImpl obj = new StudentOperationImpl();

    @WebMethod()
    public List<Attend> getAllAttend(){
        return obj.getListOfAttend();
    }

    @WebMethod()
    public Attend setAttend(Student s, Attend a){
        return obj.markAttend(s,a);
    }

    @WebMethod()
    public Student loginStudent(String code){
        return  obj.loginStudent(code);
    }
}
