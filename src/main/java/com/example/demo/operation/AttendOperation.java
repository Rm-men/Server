package com.example.demo.operation;

import com.example.demo.types.Attend;
import com.example.demo.types.Student;

import java.util.List;

public interface AttendOperation {
    List<Student> getListOfStudent();
    List<Attend> getListOfAttend();
    List<Attend> addNewAttend(Attend a);
}
