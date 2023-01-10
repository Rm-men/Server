package com.example.demo;

import com.example.demo.operation.AttendOperation_repo;
import com.example.demo.operation.AttendOperation_repo_Impl;
import com.example.demo.operation.StudentOperation_repo_Impl;
import com.example.demo.service.endpoint.StudentService;
import com.example.demo.types.Attend;
import com.example.demo.types.Student;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class TestStudentRepo {
    @Mock
    private StudentOperation_repo_Impl repo = new StudentOperation_repo_Impl();
    Date now = new Date();

    Student vyasa = new Student(0, "Вася", "Васиков", "Васильевич", 300, "8800");
    Student vyasa_noName = new Student(1, null, "Васиков", "Васильевич", 300, "8801");
    Student vyasa_noFamily = new Student(2, "Вася", null, "Васильевич", 300, "8802");
    Student vyasa_noPatr = new Student(3, "Вася", "Васиков", null, 300, "8803");
    Student vyasa_noCode = new Student(4, "Вася", "Васиков", "Васильевич", 300, null);
    Attend a1 = new Attend(0, 0, now.toString(), 0, "-");
    Attend a2 = new Attend(1, 0, now.toString(), 1, "-");

    @Test
    void conn_good() {
        Connection jdbcConnection = mock(Connection.class);
        Connection connection = repo.setConn(jdbcConnection);
        assertNotNull(connection);
        assertEquals(jdbcConnection,connection);
    }
    //region Login (4)
    @Test
    void loginStudent_good() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        Student student = vyasa;
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(student.getId());
        when(rs.getString("name")).thenReturn(student.getName());
        when(rs.getString("family")).thenReturn(student.getFamily());
        when(rs.getString("patronymic")).thenReturn(student.getPatronymic());
        when(rs.getInt("group")).thenReturn(student.getGroup());
        when(rs.getString("code")).thenReturn(student.getCode());

        PreparedStatement ps = mock(PreparedStatement.class);
        when(ps.executeQuery()).thenReturn(rs);
        when(jdbcConnection.prepareStatement(repo.GET_STUDENT_BY_CODE)).thenReturn(ps);

        repo.setConn(jdbcConnection);

        Student student1 = repo.loginStudent(student.getCode());

        assertEquals(student1.getId(),student.getId());
        assertEquals(student1.getName(),student.getName());
        assertEquals(student1.getFamily(),student.getFamily());
        assertEquals(student1.getPatronymic(),student.getPatronymic());
        assertEquals(student1.getGroup(),student.getGroup());
        assertEquals(student1.getCode(),student.getCode());
    }
    @Test
    void loginStudent_noCodeOnInput() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        Student student = vyasa;
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(false);

        PreparedStatement ps = mock(PreparedStatement.class);
        when(ps.executeQuery()).thenReturn(rs);
        when(jdbcConnection.prepareStatement(repo.GET_STUDENT_BY_CODE)).thenReturn(ps);

        Student student1 = repo.loginStudent("student.getCode()");
        Throwable thrown = assertThrows(Exception.class, () -> {
            repo.setConn(jdbcConnection);
            assertEquals(student1.getId(),student.getId());
            assertEquals(student1.getName(),student.getName());
            assertEquals(student1.getFamily(),student.getFamily());
            assertEquals(student1.getPatronymic(),student.getPatronymic());
            assertEquals(student1.getGroup(),student.getGroup());
            assertEquals(student1.getCode(),student.getCode());
            });
        assertNotNull(thrown.getMessage());
    }
    @Test
    void loginStudent_notCorrectCode() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        Student student = vyasa;
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(false);

        PreparedStatement ps = mock(PreparedStatement.class);
        when(jdbcConnection.prepareStatement(repo.GET_STUDENT_BY_CODE)).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        repo.setConn(jdbcConnection);

        Student student1 = repo.loginStudent("student.getCode()");

        assertNull(student1);
    }
    @Test
    void loginStudent_noConnect() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        Student student = vyasa;
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(student.getId());
        when(rs.getString("name")).thenReturn(student.getName());
        when(rs.getString("family")).thenReturn(student.getFamily());
        when(rs.getString("patronymic")).thenReturn(student.getPatronymic());
        when(rs.getInt("group")).thenReturn(student.getGroup());
        when(rs.getString("code")).thenReturn(student.getCode());

        PreparedStatement ps = mock(PreparedStatement.class);
        when(ps.executeQuery()).thenReturn(rs);
        when(jdbcConnection.prepareStatement(repo.GET_STUDENT_BY_CODE)).thenReturn(ps);

        Throwable thrown = assertThrows(Exception.class, () -> {
            repo.setConn(null);
            Student student1 = repo.loginStudent(student.getCode());
            assertEquals(student1.getId(),student.getId());
            assertEquals(student1.getName(),student.getName());
            assertEquals(student1.getFamily(),student.getFamily());
            assertEquals(student1.getPatronymic(),student.getPatronymic());
            assertEquals(student1.getGroup(),student.getGroup());
            assertEquals(student1.getCode(),student.getCode());
            assertNull(student1);
        });
        assertNotNull(thrown.getMessage());
    }
    //endregion

    //region MarkAttend (7)
    @Test
    void markAttend_good() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        String data = "Tue Jan 10 23:44:25 MSK 2023";
        Student student = vyasa;
        Attend attend = a1;
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true).thenReturn(false);

        when(rs.getInt("id")).thenReturn(attend.getId());
        when(rs.getInt("subject")).thenReturn(attend.getSubject());
        when(rs.getString("datetime")).thenReturn(new Date().toString());
        when(rs.getInt("student")).thenReturn(attend.getStudent());
        when(rs.getString("attend")).thenReturn(data);

        PreparedStatement ps = mock(PreparedStatement.class);
        PreparedStatement ps2 = mock(PreparedStatement.class);

        when(ps.executeQuery()).thenReturn(rs);
        when(ps2.executeQuery()).thenReturn(rs);
        when(jdbcConnection.prepareStatement(repo.SET_ATTEND)).thenReturn(ps);
        when(jdbcConnection.prepareStatement(repo.GET_ATTEND_BY_ID)).thenReturn(ps2);

        repo.setConn(jdbcConnection);

        Attend attend1 = repo.markAttend(student,attend);

        assertEquals(attend1.getId(),a1.getId());
        // assertEquals(attend1.getDatetime(),a1.getDatetime());
        assertEquals(attend1.getStudent(),a1.getStudent());
        assertEquals(attend1.getSubject(),a1.getSubject());
        assertNotEquals(attend1.getAttended(),a1.getAttended());
    }
    @Test
    void markAttend_noStudent() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        Student student = null;
        Attend attend = a1;
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(attend.getId());
        when(rs.getInt("subject")).thenReturn(attend.getSubject());
        when(rs.getString("datetime")).thenReturn(new Date().toString());
        when(rs.getInt("student")).thenReturn(attend.getStudent());
        when(rs.getString("attended")).thenReturn(attend.getAttended());

        repo.setConn(jdbcConnection);

        Throwable thrown = assertThrows(Exception.class, () -> {
            Attend attend1 = repo.markAttend(student,attend);
            assertEquals(attend1.getId(),a1.getId());
            assertEquals(attend1.getDatetime(),a1.getDatetime());
            assertEquals(attend1.getStudent(),a1.getStudent());
            assertEquals(attend1.getSubject(),a1.getSubject());
            assertNotEquals(attend1.getAttended(),a1.getAttended());
            assertNull(attend1);
        });
        assertNotNull(thrown.getMessage());
    }
    @Test
    void markAttend_noAttend() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        Student student = vyasa;
        Attend attend = null;
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(false);
/*        when(rs.getInt("id")).thenReturn(attend.getId());
        when(rs.getInt("subject")).thenReturn(attend.getSubject());
        when(rs.getString("datetime")).thenReturn(new Date().toString());
        when(rs.getInt("student")).thenReturn(attend.getStudent());
        when(rs.getString("attended")).thenReturn(attend.getAttended());*/

        repo.setConn(jdbcConnection);

        Throwable thrown = assertThrows(Exception.class, () -> {
            Attend attend1 = repo.markAttend(student,attend);
            assertEquals(attend1.getId(),a1.getId());
            assertEquals(attend1.getDatetime(),a1.getDatetime());
            assertEquals(attend1.getStudent(),a1.getStudent());
            assertEquals(attend1.getSubject(),a1.getSubject());
            assertNotEquals(attend1.getAttended(),a1.getAttended());
            assertNull(attend1);
        });
        assertNotNull(thrown.getMessage());
    }
    @Test
    void markAttend_noAttend_noStudent() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        Student student = null;
        Attend attend = null;
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(false);

        repo.setConn(jdbcConnection);

        Throwable thrown = assertThrows(Exception.class, () -> {
            Attend attend1 = repo.markAttend(student,attend);
            assertEquals(attend1.getId(),a1.getId());
            assertEquals(attend1.getDatetime(),a1.getDatetime());
            assertEquals(attend1.getStudent(),a1.getStudent());
            assertEquals(attend1.getSubject(),a1.getSubject());
            assertNotEquals(attend1.getAttended(),a1.getAttended());
            assertNull(attend1);
        });
        assertNotNull(thrown.getMessage());
    }
    @Test
    void markAttend_uncorrectStudent() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        Student student = vyasa_noCode;
        Attend attend = a1;
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(attend.getId());
        when(rs.getInt("subject")).thenReturn(attend.getSubject());
        when(rs.getString("datetime")).thenReturn(new Date().toString());
        when(rs.getInt("student")).thenReturn(attend.getStudent());
        when(rs.getString("attended")).thenReturn(attend.getAttended());

        PreparedStatement ps = mock(PreparedStatement.class);
        PreparedStatement ps2 = mock(PreparedStatement.class);

        repo.setConn(jdbcConnection);

        Throwable thrown = assertThrows(Exception.class, () -> {
            when(ps.executeQuery()).thenThrow(new Exception());
            when(ps2.executeQuery()).thenReturn(rs);
            when(jdbcConnection.prepareStatement(repo.SET_ATTEND)).thenReturn(ps);
            when(jdbcConnection.prepareStatement(repo.GET_ATTEND_BY_ID)).thenReturn(ps2);

            Attend attend1 = repo.markAttend(student,attend);

            assertEquals(attend1.getId(),a1.getId());
            assertEquals(attend1.getDatetime(),a1.getDatetime());
            assertEquals(attend1.getStudent(),a1.getStudent());
            assertEquals(attend1.getSubject(),a1.getSubject());
        });
        assertNotNull(thrown.getMessage());
    }
    @Test
    void markAttend_uncorrectAttend() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        Student student = vyasa_noCode;
        Attend attend = a2;
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(false);
/*        when(rs.getInt("id")).thenReturn(attend.getId());
        when(rs.getInt("subject")).thenReturn(attend.getSubject());
        when(rs.getString("datetime")).thenReturn(new Date().toString());
        when(rs.getInt("student")).thenReturn(attend.getStudent());
        when(rs.getString("attended")).thenReturn(attend.getAttended());*/

        PreparedStatement ps = mock(PreparedStatement.class);
        PreparedStatement ps2 = mock(PreparedStatement.class);

        repo.setConn(jdbcConnection);
        when(ps.executeQuery()).thenReturn(rs);
        when(ps2.executeQuery()).thenReturn(rs);
        when(jdbcConnection.prepareStatement(repo.SET_ATTEND)).thenReturn(ps);
        when(jdbcConnection.prepareStatement(repo.GET_ATTEND_BY_ID)).thenReturn(ps2);

        Attend attend1 = repo.markAttend(student,attend);

        assertNull(attend1);

    }
    @Test
    void markAttend_noConn() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        Student student = vyasa;
        Attend attend = a1;
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(attend.getId());
        when(rs.getInt("subject")).thenReturn(attend.getSubject());
        when(rs.getString("datetime")).thenReturn(new Date().toString());
        when(rs.getInt("student")).thenReturn(attend.getStudent());
        when(rs.getString("attended")).thenReturn(attend.getAttended());

        repo.setConn(null);

        Throwable thrown = assertThrows(Exception.class, () -> {
            Attend attend1 = repo.markAttend(student,attend);
            assertEquals(attend1.getId(),a1.getId());
            assertEquals(attend1.getDatetime(),a1.getDatetime());
            assertEquals(attend1.getStudent(),a1.getStudent());
            assertEquals(attend1.getSubject(),a1.getSubject());
            assertNotEquals(attend1.getAttended(),a1.getAttended());
            assertNull(attend1);
        });
        assertNotNull(thrown.getMessage());
    }
    //endregion

    //region GetAttend (3)
    @Test
    void getListOfAttend_first() throws Exception {
        Connection jdbcConnection = mock(Connection.class);

        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(a1.getId());
        when(rs.getInt("subject")).thenReturn(a1.getSubject());
        when(rs.getString("datetime")).thenReturn(a1.getDatetime());
        when(rs.getInt("student")).thenReturn(a1.getStudent());
        when(rs.getString("attended")).thenReturn(a1.getAttended());
        Statement statement = mock(Statement.class);
        when(statement.executeQuery(repo.GET_ATTEND_ALL)).thenReturn(rs);
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
        when(rs.next()).thenReturn(false);
        Statement statement = mock(Statement.class);
        when(statement.executeQuery(repo.GET_ATTEND_ALL)).thenReturn(rs);
        when(jdbcConnection.createStatement()).thenReturn(statement);

        repo.setConn(null);

        Throwable thrown = assertThrows(Exception.class, () ->
            repo.getListOfAttend()
        );
        assertNotNull(thrown.getMessage());
    }
    @Test
    void getAllAttend_none() throws Exception {
        Connection jdbcConnection = mock(Connection.class);

        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(false);
        Statement statement = mock(Statement.class);
        when(statement.executeQuery(repo.GET_ATTEND_ALL)).thenReturn(rs);
        when(jdbcConnection.createStatement()).thenReturn(statement);

        repo.setConn(jdbcConnection);
        List<Attend> list_repo = repo.getListOfAttend();

        assertEquals(list_repo.size(), 0);
    }
    //endregion

}
