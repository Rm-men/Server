package com.example.demo.operation;

import com.example.demo.types.Attend;
import com.example.demo.types.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface StudentOperation_repo {
    Connection setConn(Connection conn);
    List<Attend> getListOfAttend() ;
    Student loginStudent(String code) ;
    Attend markAttend(Student s, Attend a) ;
}
