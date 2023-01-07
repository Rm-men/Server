package com.example.demo;

import com.example.demo.operation.StudentOperationImpl;
import com.example.demo.service.endpoint.StudentService;
import com.example.demo.types.Attend;
import com.example.demo.types.Student;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;
import java.util.List;
import java.util.Objects;


public class StudentControllerTest {
    @InjectMocks
    private StudentService service;
    @Mock
    private StudentOperationImpl studentOperation;
    Date now = new Date();

    Student vyasa = new Student(0, "Вася", "Васиков", "Васильевич", 300, "8800");
    Student vyasa_noName = new Student(1, null, "Васиков", "Васильевич", 300, "8801");
    Student vyasa_noFamily = new Student(2, "Вася", null, "Васильевич", 300, "8802");
    Student vyasa_noPatr = new Student(3, "Вася", "Васиков", null, 300, "8803");
    Student vyasa_noCode = new Student(4, "Вася", "Васиков", "Васильевич", 300, null);
    Attend a1 = new Attend(0, 0, now.toString(), 0, "-");
    Attend a2 = new Attend(1, 0, now.toString(), 1, "-");
    Attend a3 = new Attend(2, 0, now.toString(), 2, "-");

    //region Login (3)
    @Test
    void loginStudent_good() {
        Student st = studentOperation.loginStudent("8800");
        assert (st.equals(vyasa));
    }

    @Test
    void loginStudent_noCodeOnInput() {
        Student st = studentOperation.loginStudent(null);
        assert (st == null);
    }

    @Test
    void loginStudent_notCorrectCode() {
        Student st = studentOperation.loginStudent("sdafjlludifdsi");
        assert (st == null);
    }
    //endregion

    //region MarkAttend (4)
    @Test
    void markAttend_good() {
        Attend rez = studentOperation.markAttend(vyasa, a1);
        assert (rez != null);
        assert (!Objects.equals(rez.getAttended(), "-"));
    }

    @Test
    void markAttend_noStudent() {
        Attend rez = studentOperation.markAttend(null, a1);
        assert (rez == null);
        assert (Objects.equals(a1.getAttended(), "-"));
    }

    @Test
    void markAttend_noAttend() {
        Attend rez = studentOperation.markAttend(vyasa, null);
        assert (rez == null);
        assert (Objects.equals(a1.getAttended(), "-"));
    }

    @Test
    void markAttend_noAttend_noStudent() {
        Attend rez = studentOperation.markAttend(null, null);
        assert (rez == null);
        assert (Objects.equals(a1.getAttended(), "-"));
    }

    //endregion

    //region GetAttend (1)
    @Test
    void getListOfAttend_good() {
        List<Attend> la = studentOperation.getListOfAttend();
        assert (la.get(0) == a1);
        assert (la.get(1) == a2);
        assert (la.get(2) == a3);
    }
    //endregion
}
