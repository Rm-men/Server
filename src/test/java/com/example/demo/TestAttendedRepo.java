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

import static com.example.demo.utils.JDBCUtils.getConnectJDBC;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestAttendedRepo {
    @Mock
    // private AttendOperation_repo repo = mock(AttendOperation_repo_Impl.class);
    private AttendOperation_repo repo = new AttendOperation_repo_Impl();

    @InjectMocks
    JDBCUtils jdbcUtils;
    @Mock
    public Connection mConn = mock(Connection.class);
    @Mock
    public Statement mStatement;

    Date now = new Date();
    Student vyasa = new Student(0, "Вася", "Васиков", "Васильевич", 300, "8800");
    Student ivyasa = new Student(1, "Иван", "Иванов", "Иванович", 300, "55535");

    Attend a1 = new Attend(3, 0, now.toString(), 0, "-");
    Attend a2 = new Attend(1, 0, now.toString(), 1, "-");
    Attend a3 = new Attend(2, 0, now.toString(), 2, "-");
    Attend a_noneDatetime = new Attend(3, 0, null, 3, "-");
    Attend a_noneAttend = new Attend(3, 0, now.toString(), 3, null);
    Attend a_noneAttend_noneDatetime = new Attend(3, 0, null, 3, null);

    //region GetAttends (2)
    @Test
    void getAllAttend_first() throws Exception {
        Connection jdbcConnection = mock(Connection.class);

        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(a1.getId());
        when(rs.getInt("subject")).thenReturn(a1.getSubject());
        when(rs.getString("datetime")).thenReturn(a1.getDatetime());
        when(rs.getInt("student")).thenReturn(a1.getStudent());
        when(rs.getString("attended")).thenReturn(a1.getAttended());
        Statement statement = mock(Statement.class);
        when(statement.executeQuery(repo.getGET_ALL())).thenReturn(rs);
        when(jdbcConnection.createStatement()).thenReturn(statement);

        repo.setConn(jdbcConnection);
        List<Attend> list_code = new ArrayList<>();
        list_code.add(a1);
        List<Attend> list_repo = repo.getListOfAttend();

        assertEquals(list_repo.size(), list_code.size());
        for (int i = 0; i< list_repo.size(); i++) {
            assertEquals(list_repo.get(i).getId(),list_code.get(i).getId());
            assertEquals(list_repo.get(i).getDatetime(),list_code.get(i).getDatetime());
            assertEquals(list_repo.get(i).getStudent(),list_code.get(i).getStudent());
            assertEquals(list_repo.get(i).getSubject(),list_code.get(i).getSubject());
            assertEquals(list_repo.get(i).getAttended(),list_code.get(i).getAttended());
        }
    }
    @Test
    void getAllAttend_noConn() throws Exception {
        Connection jdbcConnection =  mock(Connection.class);

        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(a1.getId());
        when(rs.getInt("subject")).thenReturn(a1.getSubject());
        when(rs.getString("datetime")).thenReturn(a1.getDatetime());
        when(rs.getInt("student")).thenReturn(a1.getStudent());
        when(rs.getString("attended")).thenReturn(a1.getAttended());
        Statement statement = mock(Statement.class);
        when(statement.executeQuery(repo.getGET_ALL())).thenReturn(rs);
        when(jdbcConnection.createStatement()).thenReturn(statement);

        repo.setConn(null);
        List<Attend> list_code = new ArrayList<>();
        list_code.add(a1);
        Throwable thrown = assertThrows(Exception.class, () -> {
            List<Attend> list_repo = repo.getListOfAttend();
            assertEquals(list_repo.size(), list_code.size());
            for (int i = 0; i< list_repo.size(); i++) {
                assertEquals(list_repo.get(i).getId(),list_code.get(i).getId());
                assertEquals(list_repo.get(i).getDatetime(),list_code.get(i).getDatetime());
                assertEquals(list_repo.get(i).getStudent(),list_code.get(i).getStudent());
                assertEquals(list_repo.get(i).getSubject(),list_code.get(i).getSubject());
                assertEquals(list_repo.get(i).getAttended(),list_code.get(i).getAttended());
            }
        });
        assertNotNull(thrown.getMessage());
    }
    //endregion
    //region NewAttend (1)
    @Test
    void setNewAttend()  {
        repo.addNewAttend(a1);
    }
}
