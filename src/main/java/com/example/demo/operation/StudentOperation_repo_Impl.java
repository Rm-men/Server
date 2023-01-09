package com.example.demo.operation;

import com.example.demo.types.Attend;
import com.example.demo.types.Student;
import com.example.demo.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentOperation_repo_Impl implements StudentOperation_repo {
    private final static String GET_STUDENT_BY_CODE = "SELECT * FROM student WHERE code = ?";
    private final static String GET_ATTEND_ALL = "SELECT * FROM attending";
    private final static String SET_ATTEND = "UPDATE attending SET attended = ? WHERE student = ? AND id = ?";
    private final static String GET_ATTEND_BY_ID = "SELECT * FROM attending WHERE id = ?";
    public StudentOperation_repo_Impl() {
    }


    @Override
    public List<Attend> getListOfAttend() {
        try (Statement stmt = JDBCUtils.getConnectJDBC().createStatement()) {
        ResultSet rs = stmt.executeQuery(GET_ATTEND_ALL);
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
    } catch (SQLException e) {
        System.out.println("IN get by id  exception: " + e.getMessage());
        return null;
    }
    }

    @Override
    public Student loginStudent(String code)  {
        try (PreparedStatement ps = JDBCUtils.getConnectJDBC().prepareStatement(GET_STUDENT_BY_CODE)){
        ps.setString(1,code);
        ResultSet rs = ps.executeQuery();
        Student stud = null;
        while (rs.next()) {
            if (stud == null)
            {
                stud = new Student(rs.getInt("id"),rs.getString("name"),
                        rs.getString("family"),rs.getString("patronymic"),rs.getInt("group"),rs.getString("code"));
            }
            else
            {
                return null;
            }

        }
        return stud;
    } catch (SQLException e) {
        System.out.println("IN get by id  exception: " + e.getMessage());
        return null;
        }
    }

    @Override
    public Attend markAttend(Student s, Attend a) {
    try (PreparedStatement ps = JDBCUtils.getConnectJDBC().prepareStatement(SET_ATTEND);
         PreparedStatement ps2 = JDBCUtils.getConnectJDBC().prepareStatement(GET_ATTEND_BY_ID)){
        java.util.Date now = new Date();
        ps.setString(1, now.toString());
        ps.setInt(2, s.getId());
        ps.setInt(3, a.getId());
        ps.executeQuery();
        ps2.setInt(1, a.getId());
        ResultSet rs = ps2.executeQuery();
        Attend at = null;
        while (rs.next()) {
            if (at == null) {
                at = new Attend(rs.getInt("id"), rs.getInt("subject"), rs.getString("datetime"),
                        rs.getInt("student"), rs.getString("attend"));
            } else {
                return null;
            }
        }
        return at;
    }
    catch (SQLException e) {
        System.out.println("IN get by id  exception: " + e.getMessage());
        return null;
        }
    }
}

