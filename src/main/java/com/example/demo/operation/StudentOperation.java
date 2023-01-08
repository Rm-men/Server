package com.example.demo.operation;

import com.example.demo.types.Attend;
import com.example.demo.types.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentOperation {
    List<Attend> getListOfAttend() throws SQLException;
    Student loginStudent(String code) throws SQLException;
    Attend markAttend(Student s, Attend a) throws SQLException;
}
