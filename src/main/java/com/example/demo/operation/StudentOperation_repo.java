package com.example.demo.operation;

import com.example.demo.types.Attend;
import com.example.demo.types.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentOperation_repo {
    List<Attend> getListOfAttend() ;
    Student loginStudent(String code) ;
    Attend markAttend(Student s, Attend a) ;
}
