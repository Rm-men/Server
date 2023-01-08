package com.example.demo.operation;

import com.example.demo.types.Attend;
import com.example.demo.types.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentOperationImpl implements StudentOperation {
    final Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
            "postgres",
            "123");

    public StudentOperationImpl() throws SQLException {
    }


    @Override
    public List<Attend> getListOfAttend() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM attending");
        List<Attend> lstAttend = new ArrayList<Attend>();
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

    @Override
    public Student loginStudent(String code) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM student WHERE code = ?");
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
    }

    @Override
    public Attend markAttend(Student s, Attend a) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE attending SET attended = ? WHERE student = ? AND id = ?");
        java.util.Date now = new Date();
        ps.setString(1,now.toString());
        ps.setInt(2,s.getId());
        ps.setInt(3,a.getId());
        ps.executeQuery();
        ps = conn.prepareStatement("SELECT * FROM attending WHERE id = ?");
        ps.setInt(1,a.getId());
        ResultSet rs = ps.executeQuery();
        Attend at = null;
        while (rs.next()) {
            if (at == null)
            {
                at = new Attend(rs.getInt("id"),rs.getInt("subject"),rs.getString("datetime"),
                        rs.getInt("student"), rs.getString("attend"));
            }
            else
            {
                return null;
            }

        }
        return at;
    }
}

