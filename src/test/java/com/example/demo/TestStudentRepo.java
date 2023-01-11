package com.example.demo;

import com.example.demo.operation.StudentOperation_repo_Impl;
import com.example.demo.types.Attend;
import com.example.demo.types.Student;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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
    Attend a2 = new Attend(1, 0, now.toString(), 1, "");
    Attend a3 = new Attend(2, 0, now.toString(), 1, null);

    @Test
    void conn_good() {
        Connection jdbcConnection = mock(Connection.class);
        Connection connection = repo.setConn(jdbcConnection);
        assertNotNull(connection);
        assertEquals(jdbcConnection, connection);
    }

    //region Login (4)
    @Test
    void loginStudent_good() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        Student student = vyasa;
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(student.id);
        when(rs.getString("name")).thenReturn(student.name);
        when(rs.getString("family")).thenReturn(student.family);
        when(rs.getString("patronymic")).thenReturn(student.patronymic);
        when(rs.getInt("group")).thenReturn(student.group);
        when(rs.getString("code")).thenReturn(student.code);

        PreparedStatement ps = mock(PreparedStatement.class);
        when(ps.executeQuery()).thenReturn(rs);
        when(jdbcConnection.prepareStatement(repo.GET_STUDENT_BY_CODE)).thenReturn(ps);

        repo.setConn(jdbcConnection);

        Student student1 = repo.loginStudent(student.code);

        assertEquals(student1.id, student.id);
        assertEquals(student1.name, student.name);
        assertEquals(student1.family, student.family);
        assertEquals(student1.patronymic, student.patronymic);
        assertEquals(student1.group, student.group);
        assertEquals(student1.code, student.code);
    }
    @Test
    void loginStudent_goodNoPatr() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        Student student = vyasa_noPatr;
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(student.id);
        when(rs.getString("name")).thenReturn(student.name);
        when(rs.getString("family")).thenReturn(student.family);
        when(rs.getString("patronymic")).thenReturn(student.patronymic);
        when(rs.getInt("group")).thenReturn(student.group);
        when(rs.getString("code")).thenReturn(student.code);

        PreparedStatement ps = mock(PreparedStatement.class);
        when(ps.executeQuery()).thenReturn(rs);
        when(jdbcConnection.prepareStatement(repo.GET_STUDENT_BY_CODE)).thenReturn(ps);

        repo.setConn(jdbcConnection);

        Student student1 = repo.loginStudent(student.code);

        assertEquals(student1.id, student.id);
        assertEquals(student1.name, student.name);
        assertEquals(student1.family, student.family);
        assertEquals(student1.patronymic, student.patronymic);
        assertEquals(student1.group, student.group);
        assertEquals(student1.code, student.code);
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
            assertEquals(student1.id, student.id);
            assertEquals(student1.name, student.name);
            assertEquals(student1.family, student.family);
            assertEquals(student1.patronymic, student.patronymic);
            assertEquals(student1.group, student.group);
            assertEquals(student1.code, student.code);
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
    void loginStudent_nullCode() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        Student student = vyasa;
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(false);

        PreparedStatement ps = mock(PreparedStatement.class);
        when(jdbcConnection.prepareStatement(repo.GET_STUDENT_BY_CODE)).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        repo.setConn(jdbcConnection);

        Student student1 = repo.loginStudent(null);

        assertNull(student1);
    }
    @Test
    void loginStudent_noConnect() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        Student student = vyasa;
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(student.id);
        when(rs.getString("name")).thenReturn(student.name);
        when(rs.getString("family")).thenReturn(student.family);
        when(rs.getString("patronymic")).thenReturn(student.patronymic);
        when(rs.getInt("group")).thenReturn(student.group);
        when(rs.getString("code")).thenReturn(student.code);

        PreparedStatement ps = mock(PreparedStatement.class);
        when(ps.executeQuery()).thenReturn(rs);
        when(jdbcConnection.prepareStatement(repo.GET_STUDENT_BY_CODE)).thenReturn(ps);

        Throwable thrown = assertThrows(Exception.class, () -> {
            repo.setConn(null);
            Student student1 = repo.loginStudent(student.code);
            assertEquals(student1.id, student.id);
            assertEquals(student1.name, student.name);
            assertEquals(student1.family, student.family);
            assertEquals(student1.patronymic, student.patronymic);
            assertEquals(student1.group, student.group);
            assertEquals(student1.code, student.code);
            assertNull(student1);
        });
        assertNotNull(thrown.getMessage());
    }
    @Test
    void loginStudent_noFamily() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        Student student = vyasa_noFamily;
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(false);

        PreparedStatement ps = mock(PreparedStatement.class);
        when(jdbcConnection.prepareStatement(repo.GET_STUDENT_BY_CODE)).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        repo.setConn(jdbcConnection);

        Student student1 = repo.loginStudent(student.code);

        assertNull(student1);
    }
    //endregion

    //region MarkAttend (7)
    @Test
    void markAttend_good() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        String data = "Tue Jan 10 23:44:25 MSK 2023";
        Student student = vyasa;
        Attend attend = a3;
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true).thenReturn(false);

        when(rs.getInt("id")).thenReturn(attend.id);
        when(rs.getInt("subject")).thenReturn(attend.subject);
        when(rs.getString("datetime")).thenReturn(new Date().toString());
        when(rs.getInt("student")).thenReturn(attend.student);
        when(rs.getString("attend")).thenReturn(data);

        PreparedStatement ps = mock(PreparedStatement.class);
        PreparedStatement ps2 = mock(PreparedStatement.class);

        when(ps.executeQuery()).thenReturn(rs);
        when(ps2.executeQuery()).thenReturn(rs);
        when(jdbcConnection.prepareStatement(repo.SET_ATTEND)).thenReturn(ps);
        when(jdbcConnection.prepareStatement(repo.GET_ATTEND_BY_ID)).thenReturn(ps2);

        repo.setConn(jdbcConnection);

        Attend attend1 = repo.markAttend(student.id, attend.id);

        assertEquals(attend1.id, attend.id);
        // assertEquals(attend1.getDatetime(),a1.getDatetime());
        assertEquals(attend1.student, attend.student);
        assertEquals(attend1.subject, attend.subject);
        assertNotEquals(attend1.attended, attend.attended);
    }
    @Test
    void markAttend_noNameStudent() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        Student student = vyasa_noName;
        Attend attend = a1;
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(attend.id);
        when(rs.getInt("subject")).thenReturn(attend.subject);
        when(rs.getString("datetime")).thenReturn(new Date().toString());
        when(rs.getInt("student")).thenReturn(attend.student);
        when(rs.getString("attend")).thenReturn(attend.attended);

        repo.setConn(jdbcConnection);

        Throwable thrown = assertThrows(Exception.class, () -> {
            Attend attend1 = repo.markAttend(student.id, attend.id);
            assertEquals(attend1.id, a1.id);
            assertEquals(attend1.datetime, a1.datetime);
            assertEquals(attend1.student, a1.student);
            assertEquals(attend1.subject, a1.subject);
            assertNotEquals(attend1.attended, a1.attended);
            assertNull(attend1);
        });
        assertNotNull(thrown.getMessage());
    }
    @Test
    void markAttend_noStudent() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        Student student = null;
        Attend attend = a1;
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(attend.id);
        when(rs.getInt("subject")).thenReturn(attend.subject);
        when(rs.getString("datetime")).thenReturn(new Date().toString());
        when(rs.getInt("student")).thenReturn(attend.student);
        when(rs.getString("attend")).thenReturn(attend.attended);

        repo.setConn(jdbcConnection);

        Throwable thrown = assertThrows(Exception.class, () -> {
            Attend attend1 = repo.markAttend(student.id, attend.id);
            assertEquals(attend1.id, a1.id);
            assertEquals(attend1.datetime, a1.datetime);
            assertEquals(attend1.student, a1.student);
            assertEquals(attend1.subject, a1.subject);
            assertNotEquals(attend1.attended, a1.attended);
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
            Attend attend1 = repo.markAttend(student.id, attend.id);
            assertEquals(attend1.id, a1.id);
            assertEquals(attend1.datetime, a1.datetime);
            assertEquals(attend1.student, a1.student);
            assertEquals(attend1.subject, a1.subject);
            assertNotEquals(attend1.attended, a1.attended);
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
            Attend attend1 = repo.markAttend(student.id, attend.id);
            assertEquals(attend1.id, a1.id);
            assertEquals(attend1.datetime, a1.datetime);
            assertEquals(attend1.student, a1.student);
            assertEquals(attend1.subject, a1.subject);
            assertNotEquals(attend1.attended, a1.attended);
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
        when(rs.getInt("id")).thenReturn(attend.id);
        when(rs.getInt("subject")).thenReturn(attend.subject);
        when(rs.getString("datetime")).thenReturn(new Date().toString());
        when(rs.getInt("student")).thenReturn(attend.student);
        when(rs.getString("attend")).thenReturn(attend.attended);

        PreparedStatement ps = mock(PreparedStatement.class);
        PreparedStatement ps2 = mock(PreparedStatement.class);

        repo.setConn(jdbcConnection);

        Throwable thrown = assertThrows(Exception.class, () -> {
            when(ps.executeQuery()).thenThrow(new Exception());
            when(ps2.executeQuery()).thenReturn(rs);
            when(jdbcConnection.prepareStatement(repo.SET_ATTEND)).thenReturn(ps);
            when(jdbcConnection.prepareStatement(repo.GET_ATTEND_BY_ID)).thenReturn(ps2);

            Attend attend1 = repo.markAttend(student.id, attend.id);

            assertEquals(attend1.id, a1.id);
            assertEquals(attend1.datetime, a1.datetime);
            assertEquals(attend1.student, a1.student);
            assertEquals(attend1.subject, a1.subject);
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

        Attend attend1 = repo.markAttend(student.id, attend.id);

        assertNull(attend1);

    }
    @Test
    void markAttend_noConn() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        Student student = vyasa;
        Attend attend = a1;
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(attend.id);
        when(rs.getInt("subject")).thenReturn(attend.subject);
        when(rs.getString("datetime")).thenReturn(new Date().toString());
        when(rs.getInt("student")).thenReturn(attend.student);
        when(rs.getString("attend")).thenReturn(attend.attended);

        repo.setConn(null);

        Throwable thrown = assertThrows(Exception.class, () -> {
            Attend attend1 = repo.markAttend(student.id, attend.id);
            assertEquals(attend1.id, a1.id);
            assertEquals(attend1.datetime, a1.datetime);
            assertEquals(attend1.student, a1.student);
            assertEquals(attend1.subject, a1.subject);
            assertNotEquals(attend1.attended, a1.attended);
            assertNull(attend1);
        });
        assertNotNull(thrown.getMessage());
    }
    //endregion

    //region GetAttend (4)
    @Test
    void getListOfAttend_good() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        Attend attend = a1;
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(attend.id);
        when(rs.getInt("subject")).thenReturn(attend.subject);
        when(rs.getString("datetime")).thenReturn(attend.datetime);
        when(rs.getInt("student")).thenReturn(attend.student);
        when(rs.getString("attended")).thenReturn(attend.attended);
        PreparedStatement ps = mock(PreparedStatement.class);
        when(jdbcConnection.prepareStatement(repo.GET_ATTEND_FROM)).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        repo.setConn(jdbcConnection);
        List<Attend> list_code = new ArrayList<>();
        list_code.add(attend);
        List<Attend> list_repo = repo.getListOfAttend(vyasa.id);

        assertEquals(list_repo.size(), list_code.size());
        for (int i = 0; i < list_repo.size(); i++) {
            assertEquals(list_repo.get(i).id, list_code.get(i).id);
            assertEquals(list_repo.get(i).datetime, list_code.get(i).datetime);
            assertEquals(list_repo.get(i).student, list_code.get(i).student);
            assertEquals(list_repo.get(i).subject, list_code.get(i).subject);
            assertEquals(list_repo.get(i).attended, list_code.get(i).attended);
        }
    }

    @Test
    void getAllAttend_noConn() throws Exception {
        Connection jdbcConnection = mock(Connection.class);

        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(false);
        Statement statement = mock(Statement.class);
        when(statement.executeQuery(repo.GET_ATTEND_FROM)).thenReturn(rs);
        when(jdbcConnection.createStatement()).thenReturn(statement);

        repo.setConn(null);
        List<Attend> list_code = new ArrayList<>();
        list_code.add(a1);
        Throwable thrown = assertThrows(Exception.class, () -> {
                    List<Attend> list_repo = repo.getListOfAttend(vyasa.id);
                    for (int i = 0; i < list_repo.size(); i++) {
                        assertEquals(list_repo.get(i).id, list_code.get(i).id);
                        assertEquals(list_repo.get(i).datetime, list_code.get(i).datetime);
                        assertEquals(list_repo.get(i).student, list_code.get(i).student);
                        assertEquals(list_repo.get(i).subject, list_code.get(i).subject);
                        assertEquals(list_repo.get(i).attended, list_code.get(i).attended);
                    }
                }
        );
        assertNotNull(thrown.getMessage());
    }

    @Test
    void getAllAttend_noFindAttend() throws Exception {
        Connection jdbcConnection = mock(Connection.class);
        Attend attend = a1;
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(false);
        PreparedStatement ps = mock(PreparedStatement.class);
        when(jdbcConnection.prepareStatement(repo.GET_ATTEND_FROM)).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        repo.setConn(jdbcConnection);

        List<Attend> list_repo = repo.getListOfAttend(vyasa.id);

        assertTrue(list_repo.isEmpty());
    }

/*    @Test
    void getAllAttend_nullStudent() throws Exception {
        Connection jdbcConnection = mock(Connection.class);

        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(false);
        Statement statement = mock(Statement.class);
        when(statement.executeQuery(repo.GET_ATTEND_FROM)).thenReturn(rs);
        when(jdbcConnection.createStatement()).thenReturn(statement);

        repo.setConn(jdbcConnection);
        List<Attend> list_code = new ArrayList<>();
        Throwable thrown = assertThrows(Exception.class, () -> {
                    List<Attend> list_repo = repo.getListOfAttend(null);
                    for (Attend attend : list_repo) {
                        assertNotNull(attend.datetime);
                        assertNotNull(attend.attended);
                    }
                }
        );
        assertNotNull(thrown.getMessage());
    }*/

    //endregion

}
