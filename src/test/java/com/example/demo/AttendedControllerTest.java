package com.example.demo;

import com.example.demo.operation.AttendOperation_repo;
import com.example.demo.operation.AttendOperation_repo_Impl;
import com.example.demo.service.endpoint.AttendService;
import com.example.demo.types.Attend;
import com.example.demo.types.Student;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AttendedControllerTest {
    @Mock
    private AttendOperation_repo attendOp_repo = mock(AttendOperation_repo_Impl.class);
    @InjectMocks
    private AttendService attendService = new AttendService(attendOp_repo);

    @Mock
    private Connection mConn;
    @Mock
    private Statement mockStatement;

    Date now = new Date();
    Student vyasa = new Student(0, "Вася", "Васиков", "Васильевич", 300, "8800");
    Student ivyasa = new Student(1, "Иван", "Иванов", "Иванович", 300, "55535");

    Attend a1 = new Attend(0, 0, now.toString(), 0, "-");
    Attend a2 = new Attend(1, 0, now.toString(), 1, "-");
    Attend a3 = new Attend(2, 0, now.toString(), 2, "-");
    Attend a_noneDate = new Attend(3, 0, null, 3, "-");
    Attend a_noneAttend = new Attend(3, 0, now.toString(), 3, null);
    Attend a_noneAttend_noneDate = new Attend(3, 0, null, 3, null);


    //region GetAttends (2)
    @Test
    void getAllAttend_empty() {
        List<Attend> la = attendOp_repo.getListOfAttend();
        assert (la.size() == 0);
    }
    @Test
    void getAllAttend_noEmpty()  {
        List<Attend> la = new ArrayList<>();
        when(attendService.getAllAttend()).thenReturn(la);
        assertEquals (la.size() , 0);
        la = attendOp_repo.addNewAttend(a1);
        assertEquals (la.size() , 1);
        assertEquals (la.get(0) , a1);
    }
    //endregion

    //region NewAttend (6)
    @Test
    void setNewAttend_first()  {
        Attend added = a1;
        attendOp_repo.addNewAttend(added);
        List<Attend> la = attendOp_repo.getListOfAttend();
        assertEquals (la.get(0) , added);
    }
    @Test
    void setNewAttend_noFirst()  {
        Attend added = a2;
        attendOp_repo.addNewAttend(a1);
        attendOp_repo.addNewAttend(added);
        List<Attend> la = attendOp_repo.getListOfAttend();
        assertEquals (la.get(0) , a1);
        assertEquals (la.get(1) , added);
    }
    @Test
    void setNewAttend_null()  {
        Attend added = null;
        List<Attend> la;
        List<Attend> la1;
        List<Attend> la2 = null;
        la = attendOp_repo.addNewAttend(a1);
        la1 = attendOp_repo.addNewAttend(added);
        la2.add((Attend) la);
        la2.add((Attend) la1);
        assertEquals (la2.size() , 1);

    }
    @Test
    void setNewAttend_noneDate()  {
        Attend added = a_noneDate;
        attendOp_repo.addNewAttend(a1);
        attendOp_repo.addNewAttend(added);
        List<Attend> la = attendOp_repo.getListOfAttend();
        assertEquals (la.get(0) , a1);
        assertEquals (la.size() , 1);
    }
    @Test
    void setNewAttend_noneDate_noAttend()  {
        Attend added = a_noneAttend_noneDate;
        attendOp_repo.addNewAttend(a1);
        attendOp_repo.addNewAttend(added);
        List<Attend> la = attendOp_repo.getListOfAttend();
        assertEquals (la.get(0) , a1);
        assertEquals (la.size() , 1);
    }
    @Test
    void setNewAttend_noneAttend()  {
        Attend added = a_noneAttend;
        attendOp_repo.addNewAttend(added);
        List<Attend> la = attendOp_repo.getListOfAttend();
        Attend add = la.get(0);
        assert (
                add.getId() == a_noneAttend.getId() &
                        Objects.equals(add.getDatetime(), a_noneAttend.getDatetime()) &
                add.getStudent() == a_noneAttend.getStudent() &
                add.getSubject() == a_noneAttend.getSubject() &
                        Objects.equals(add.getAttended(), "-")
        );
        assertEquals (la.size() , 1);
    }
    //endregion
}
