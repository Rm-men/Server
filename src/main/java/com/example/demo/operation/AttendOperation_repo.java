package com.example.demo.operation;

import com.example.demo.types.Attend;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AttendOperation_repo {
    String getGET_ALL();

    void setConn(Connection conn);

    List<Attend> getListOfAttend();

    List<Attend> addNewAttend(Attend a);
}
