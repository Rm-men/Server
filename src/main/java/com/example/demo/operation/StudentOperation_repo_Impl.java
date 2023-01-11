package com.example.demo.operation;

import com.example.demo.types.Attend;
import com.example.demo.types.Student;

import java.beans.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class StudentOperation_repo_Impl implements StudentOperation_repo {
    public String GET_STUDENT_BY_CODE = "SELECT * FROM student WHERE code = ?";
    public String GET_ATTEND_FROM = "SELECT * FROM attending WHERE student = ?";
    public String SET_ATTEND = "UPDATE attending SET attended = ? WHERE student = ? AND id = ?";
    public String GET_ATTEND_BY_ID = "SELECT * FROM attending WHERE id = ?";
    public String GET_ONLY_ATTEND_BY_ID = "SELECT attended FROM attending WHERE id = ?";

    Connection _conn;

    @Override
    public Connection setConn(Connection conn) {
        _conn = conn;
        return _conn;}
    @Override
    public List<Attend> getListOfAttend(int st) {
        try (PreparedStatement ps = _conn.prepareStatement(GET_ATTEND_FROM)) {
            ps.setInt(1, st);
            ResultSet rs = ps.executeQuery();
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

    @Override
    public Student loginStudent(String code) {
        try (PreparedStatement ps = _conn.prepareStatement(GET_STUDENT_BY_CODE)) {
            if (Objects.equals(code, "") || code == null)
                throw new Exception();
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            Student st = null;
            while (rs.next()) {
                st = new Student(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("family"),
                        rs.getString("patronymic"),
                        rs.getInt("group"),
                        rs.getString("code"));
            }
            return st;
        } catch (Exception e) {
            System.out.println("IN get by id  exception: " + e.getMessage());
            return null;
        }
    }


    @Override
    public Attend markAttend(int s, int a) {
        try (PreparedStatement ps0 = _conn.prepareStatement(GET_ONLY_ATTEND_BY_ID);
                PreparedStatement ps = _conn.prepareStatement(SET_ATTEND);
             PreparedStatement ps2 = _conn.prepareStatement(GET_ATTEND_BY_ID)) {
            ps.setString(1, new Date().toString());
            ps.setInt(2, s);
            ps.setInt(3, a);
            ps0.setInt(1,a);
            ResultSet rs0 = ps0.executeQuery();
            while (rs0.next()) {
                String atttt = rs0.getString("attended");
                if (!(Objects.equals(atttt, "") || Objects.equals(atttt, "-") || atttt == null))
                    return null;
            }
            ps.executeUpdate();
            ps2.setInt(1,a);
            ResultSet rs = ps2.executeQuery();
            Attend at = null;
            while (rs.next()) {
                at = new Attend(rs.getInt("id"),
                        rs.getInt("subject"),
                        rs.getString("datetime"),
                        rs.getInt("student"),
                        rs.getString("attended"));
            }
            return at;
        } catch (Exception e) {
            System.out.println("IN get by id  exception: " + e.getMessage());
            return null;
        }
    }
}
