package com.example.demo.operation;

import com.example.demo.types.Attend;

import java.sql.SQLException;
import java.util.List;

public interface AttendOperation_repo {
    List<Attend> getListOfAttend();
    List<Attend> addNewAttend(Attend a);
}
