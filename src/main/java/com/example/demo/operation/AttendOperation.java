package com.example.demo.operation;

import com.example.demo.types.Attend;
import com.example.demo.types.Student;

import java.sql.SQLException;
import java.util.List;

public interface AttendOperation {
    List<Attend> getListOfAttend() throws SQLException;
    List<Attend> addNewAttend(Attend a) throws SQLException;
}
