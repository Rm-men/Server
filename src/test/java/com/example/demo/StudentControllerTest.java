package com.example.demo;

import com.example.demo.operation.StudentOperationImpl;
import com.example.demo.service.endpoint.StudentService;
import com.example.demo.types.Student;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


public class StudentControllerTest {
   @InjectMocks
    private StudentService service;
    @Mock
    private StudentOperationImpl studentOperation;
    // Date now = new Date();

    Student vyasa = new Student(0,"Вася","Васиков","Васильевич",300,"8800");
    Student vyasa_noName = new Student(1,null,"Васиков","Васильевич",300,"8801");
    Student vyasa_noFamily = new Student(2,"Вася",null,"Васильевич",300,"8802");
    Student vyasa_noPatr = new Student(3,"Вася","Васиков",null,300,"8803");
    Student vyasa_noCode = new Student(4,"Вася","Васиков","Васильевич",300,null);
    // Attend a1 = new Attend(0,0, now.toString(), 0,"-");

    //region Login
    @Test
    void loginStudent_good(){

    }
    @Test
    void loginStudent_noCodeOnInput(){

    }
    @Test
    void loginStudent_notCorrectCode(){

    }
    //endregion


    //region MarkAttend
    @Test
    void markAttend_good(){

    }
    @Test
    void markAttend_noStudent(){

    }
    @Test
    void markAttend_noAttend(){

    }
    @Test
    void markAttend_noAttend_noStudent(){

    }
    //endregion

    //region GetAttend
    @Test
    void getListOfAttend_good(){

    }
    //endregion
}
