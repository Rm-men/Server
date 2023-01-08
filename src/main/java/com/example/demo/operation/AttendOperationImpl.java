package com.example.demo.operation;

import com.example.demo.types.Attend;
import com.example.demo.types.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendOperationImpl implements AttendOperation {
    final Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
            "postgres",
            "123");

    public AttendOperationImpl() throws SQLException {
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
    public List<Attend> addNewAttend(Attend a) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO attending VALUES (?,?,?,?,?)");
        ps.setInt(1,a.getId());
        ps.setInt(2,a.getSubject());
        ps.setString(3,a.getDatetime());
        ps.setInt(4,a.getStudent());
        ps.setString(5,a.getAttended());
        ps.addBatch();
        ps.executeBatch();
        return getListOfAttend();
    }
}

