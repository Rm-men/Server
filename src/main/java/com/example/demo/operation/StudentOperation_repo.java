package com.example.demo.operation;

import com.example.demo.types.Attend;
import com.example.demo.types.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface StudentOperation_repo {
    Connection setConn(Connection conn);

    List<Attend> getListOfAttend(int st);

    Student loginStudent(String code) ;
    Attend markAttend(int sid, int aid) ;

}
