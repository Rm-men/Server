package com.example.demo.operation;

import com.example.demo.types.Attend;
import com.example.demo.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendOperation_repo_Impl implements AttendOperation_repo {
    @Override
    public String getGET_ALL() {
        return GET_ALL;
    }

    final static String GET_ALL = "SELECT * FROM attending";

    public String getADD_NEW_ATTEND() {
        return ADD_NEW_ATTEND;
    }

    final static String ADD_NEW_ATTEND = "INSERT INTO attending VALUES (?,?,?,?,?)";
    Connection _conn;
    @Override
    public void setConn(Connection conn){
        _conn = conn;
    }

    @Override
    public List<Attend> getListOfAttend() {
        try (Statement stmt = _conn.createStatement()){
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
        }
        catch (Exception e) {
            System.out.println("IN get by id  exception: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Attend> addNewAttend(Attend a){
        try (PreparedStatement ps = JDBCUtils.getConnectJDBC().prepareStatement(ADD_NEW_ATTEND)){
            ps.setInt(1, a.getId());
            ps.setInt(2, a.getSubject());
            ps.setString(3, a.getDatetime());
            ps.setInt(4, a.getStudent());
            ps.setString(5, a.getAttended());
            ps.addBatch();
            ps.executeBatch();
            return getListOfAttend();
            // возвращать массив интов
        }
                catch (SQLException e) {
            System.out.println("IN get by id  exception: " + e.getMessage());
            return null;
        }
        // return null;
    }
}

