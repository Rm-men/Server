package com.example.demo;

import com.example.demo.operation.AttendOperation_repo;
import com.example.demo.operation.AttendOperation_repo_Impl;
import com.example.demo.service.endpoint.AttendService;
import com.example.demo.types.Attend;
import com.example.demo.types.Student;
import com.example.demo.utils.JDBCUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestAttendedRepo {
    @Mock
    private AttendOperation_repo_Impl repo = new AttendOperation_repo_Impl();

    Date now = new Date();
    Student vyasa = new Student(0, "Вася", "Васиков", "Васильевич", 300, "8800");
    Student ivyasa = new Student(1, "Иван", "Иванов", "Иванович", 300, "55535");

    Attend a1 = new Attend(3, 0, now.toString(), 0, "-");
    Attend a2 = new Attend(1, 0, now.toString(), 1, "-");
    Attend a3 = new Attend(2, 0, now.toString(), 2, "-");
    Attend a_noneDatetime = new Attend(3, 0, null, 3, "-");
    Attend a_noneAttend = new Attend(3, 0, now.toString(), 3, null);
    Attend a_noneAttend_noneDatetime = new Attend(3, 0, null, 3, null);

    @Test
    void conn_good() {
        Connection jdbcConnection = mock(Connection.class);
        Connection connection = repo.setConn(jdbcConnection);
        assertNotNull(connection);
        assertEquals(jdbcConnection,connection);
    }
    //region GetAttends (3)
    @Test
    void getAllAttend_first() throws Exception {
        Connection jdbcConnection = mock(Connection.class);

        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(a1.id);
        when(rs.getInt("subject")).thenReturn(a1.subject);
        when(rs.getString("datetime")).thenReturn(a1.datetime);
        when(rs.getInt("student")).thenReturn(a1.student);
        when(rs.getString("attended")).thenReturn(a1.attended);
        Statement statement = mock(Statement.class);
        when(statement.executeQuery(repo.GET_ALL)).thenReturn(rs);
        when(jdbcConnection.createStatement()).thenReturn(statement);

        repo.setConn(jdbcConnection);
        List<Attend> list_code = new ArrayList<>();
        list_code.add(a1);
        List<Attend> list_repo = repo.getListOfAttend();

        assertEquals(list_repo.size(), list_code.size());
        for (int i = 0; i< list_repo.size(); i++) {
            assertEquals(list_repo.get(i).id,list_code.get(i).id);
            assertEquals(list_repo.get(i).datetime,list_code.get(i).datetime);
            assertEquals(list_repo.get(i).student,list_code.get(i).student);
            assertEquals(list_repo.get(i).subject,list_code.get(i).subject);
            assertEquals(list_repo.get(i).attended,list_code.get(i).attended);
        }
    }
    @Test
    void getAllAttend_noConn() throws Exception {
        Connection jdbcConnection =  mock(Connection.class);

        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(a1.id);
        when(rs.getInt("subject")).thenReturn(a1.subject);
        when(rs.getString("datetime")).thenReturn(a1.datetime);
        when(rs.getInt("student")).thenReturn(a1.student);
        when(rs.getString("attended")).thenReturn(a1.attended);
        Statement statement = mock(Statement.class);
        when(statement.executeQuery(repo.GET_ALL)).thenReturn(rs);
        when(jdbcConnection.createStatement()).thenReturn(statement);

        List<Attend> list_code = new ArrayList<>();
        list_code.add(a1);
        Throwable thrown = assertThrows(Exception.class, () -> {
            repo.setConn(null);
            List<Attend> list_repo = repo.getListOfAttend();
            assertEquals(list_repo.size(), list_code.size());
            for (int i = 0; i< list_repo.size(); i++) {
                assertEquals(list_repo.get(i).id,list_code.get(i).id);
                assertEquals(list_repo.get(i).datetime,list_code.get(i).datetime);
                assertEquals(list_repo.get(i).student,list_code.get(i).student);
                assertEquals(list_repo.get(i).subject,list_code.get(i).subject);
                assertEquals(list_repo.get(i).attended,list_code.get(i).attended);
            }
        });
        assertNotNull(thrown.getMessage());
    }
    @Test
    void getAllAttend_none() throws Exception {
        Connection jdbcConnection = mock(Connection.class);

        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(false);
        Statement statement = mock(Statement.class);
        when(statement.executeQuery(repo.GET_ALL)).thenReturn(rs);
        when(jdbcConnection.createStatement()).thenReturn(statement);

        repo.setConn(jdbcConnection);
        List<Attend> list_repo = repo.getListOfAttend();

        assertEquals(list_repo.size(), 0);
    }
    //endregion
    /*//region NewAttend (6)
    @Test
    void setNewAttend_first()  {
        assertEquals(1,2);
    }
    @Test
    void setNewAttend_noFirst()  {
        assertEquals(1,2);
    }
    @Test
    void setNewAttend_null()  {
        assertEquals(1,2);
    }
    @Test
    void setNewAttend_noneDate()  {
        assertEquals(1,2);
    }
    @Test
    void setNewAttend_noneDate_noAttend()  {
        assertEquals(1,2);
    }
    @Test
    void setNewAttend_noneAttend()  {
        assertEquals(1,2);
    }
    //endregion*/
}
