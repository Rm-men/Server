package com.example.demo.operation;

import com.example.demo.types.Attend;
import com.example.demo.types.Student;

import java.util.List;

public interface StudentOperation {
    List<Attend> getListOfAttend();
    Student loginStudent(String code);
    Attend markAttend(Student s, Attend a);
}
