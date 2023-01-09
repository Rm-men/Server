package com.example.demo.operation;

import com.example.demo.types.Attend;
import com.example.demo.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendOperation_repo_Impl implements AttendOperation_repo {

    private final static String GET_ALL = "SELECT * FROM attending";
    private final static String ADD_NEW_ATTEND = "INSERT INTO attending VALUES (?,?,?,?,?)";


    public AttendOperation_repo_Impl(){
    }

    @Override
    public List<Attend> getListOfAttend() {
        try (Statement stmt = JDBCUtils.getConnectJDBC().createStatement()){
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
        catch (SQLException e) {
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
        }
                catch (SQLException e) {
            System.out.println("IN get by id  exception: " + e.getMessage());
            return null;
        }
    }
}

