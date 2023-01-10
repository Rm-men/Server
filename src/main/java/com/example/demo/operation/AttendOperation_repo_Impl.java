package com.example.demo.operation;

import com.example.demo.types.Attend;
import com.example.demo.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendOperation_repo_Impl implements AttendOperation_repo {

    public String GET_ALL = "SELECT * FROM attending";

    public String ADD_NEW_ATTEND = "INSERT INTO attending VALUES (?,?,?,?,?)";
    Connection _conn;

    @Override
    public Connection setConn(Connection conn) {
        _conn = conn;
        return _conn;
    }

    @Override
    public List<Attend> getListOfAttend() {
        try (Statement stmt = _conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(GET_ALL);
            List<Attend> lstAttend = new ArrayList<>();
            while (rs.next()) {
                Attend at = new Attend(
                        rs.getInt("id"),
                        rs.getInt("subject"),
                        rs.getString("datetime"),
                        rs.getInt("student"),
                        rs.getString("attended"));
                lstAttend.add(at);
            }
            return lstAttend;
        } catch (Exception e) {
            System.out.println("IN get by id  exception: " + e.getMessage());
            return null;
        }
    }
}
