package com.example.demo;

import com.example.demo.operation.StudentOperation_repo_Impl;
import com.example.demo.service.endpoint.StudentService;
import com.example.demo.types.Attend;
import com.example.demo.types.Student;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;
import java.util.List;


public class TestStudentRepo {
    @InjectMocks
    private StudentService service;
    @Mock
    private StudentOperation_repo_Impl repo;
    Date now = new Date();

    Student vyasa = new Student(0, "Вася", "Васиков", "Васильевич", 300, "8800");
    Student vyasa_noName = new Student(1, null, "Васиков", "Васильевич", 300, "8801");
    Student vyasa_noFamily = new Student(2, "Вася", null, "Васильевич", 300, "8802");
    Student vyasa_noPatr = new Student(3, "Вася", "Васиков", null, 300, "8803");
    Student vyasa_noCode = new Student(4, "Вася", "Васиков", "Васильевич", 300, null);
    Attend a1 = new Attend(0, 0, now.toString(), 0, "-");
    Attend a2 = new Attend(1, 0, now.toString(), 1, "-");
    Attend a3 = new Attend(2, 0, now.toString(), 2, "-");


    //region Login (1)
    @Test
    void loginStudent_good() {
        repo.loginStudent("8800");
    }
    //endregion

    //region MarkAttend (1)
    @Test
    void markAttend_good() {
        repo.markAttend(vyasa, a1);
    }
    //endregion

    //region GetAttend (1)
    @Test
    void getListOfAttend_good() {
        repo.getListOfAttend();
    }
    //endregion

}
