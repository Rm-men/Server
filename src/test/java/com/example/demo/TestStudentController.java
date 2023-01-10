package com.example.demo;


import com.example.demo.operation.StudentOperation_repo;
import com.example.demo.operation.StudentOperation_repo_Impl;
import com.example.demo.service.endpoint.StudentService;
import com.example.demo.types.Attend;
import com.example.demo.types.Student;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class TestStudentController {
    @Mock
    private StudentOperation_repo repo = mock(StudentOperation_repo_Impl.class);
    @InjectMocks
    private StudentService service = new StudentService(repo);
    Date now = new Date();

    Student vyasa = new Student(0, "Вася", "Васиков", "Васильевич", 300, "8800");
    Attend a1 = new Attend(0, 0, now.toString(), 0, "-");

    public TestStudentController() throws SQLException {
    }

    //region Login (1)
    @Test
    void loginStudent() {
        when(repo.loginStudent(vyasa.code)).thenReturn(vyasa);

        Student st = service.loginStudent(vyasa.code);

        assertEquals (st,vyasa);
    }
    //endregion

    //region MarkAttend (1)
    @Test
    void markAttend() {
        Attend a1_1 = new Attend(0, 0, now.toString(), 0, new Date().toString());

        when(repo.markAttend(vyasa,a1)).thenReturn(a1_1);

        Attend rez = service.setAttend(vyasa, a1);
        assertEquals(a1_1, rez);
    }
    //endregion

    //region getAllAttend (1)
    @Test
    void getAllAttend() {
        List<Attend> a1 = new ArrayList<>();
        a1.add(this.a1);

        when(repo.getListOfAttend(vyasa)).thenReturn(a1);

        List<Attend> a2 = service.getAllAttendForStudent(vyasa);
        assertEquals(a1, a2);
    }
    //endregion
}
