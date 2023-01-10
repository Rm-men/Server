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

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TestAttendedController {
    @Mock
    private AttendOperation_repo repo = mock(AttendOperation_repo_Impl.class);
    @InjectMocks
    private AttendService service = new AttendService(repo);

    @Mock
    private Connection mConn;
    @Mock
    private Statement mockStatement;

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
    void getAllAttend_empty() {
        List<Attend> la = new ArrayList<>();
        when(repo.getListOfAttend()).thenReturn(la);
        List<Attend> la2 = service.getAllAttend();
        assertEquals (la2 , la);
    }
    @Test
    void getAllAttend_noEmpty()  {
        List<Attend> la = new ArrayList<>();
        la.add(a1);
        la.add(a2);
        when(repo.getListOfAttend()).thenReturn(la);
        List<Attend> la2 = service.getAllAttend();
        assertEquals (la , la2);
    }
    //endregion
    //region NewAttend (6)
    @Test
    void setNewAttend_first()  {
        Attend added = a1;
        List<Attend> la_repo = new ArrayList<>();
        when(repo.getListOfAttend()).thenReturn(la_repo);
        // Изначально репо пустой
        List<Attend> la_servGet_onNuls = service.getAllAttend();
        assertEquals(la_servGet_onNuls.size(), 0);
        // Добавляется атенд
        la_repo.add(added);
        assertNotNull(service.setNewAttend(added));
    }
/*    @Test
    void setNewAttend_noFirst()  {
        Attend added = a1;
        Attend added2 = a2;

        List<Attend> la_repo = new ArrayList<>();
        la_repo.add(added);

        when(repo.getListOfAttend()).thenReturn(la_repo);
        List<Attend> la_servGet = service.getAllAttend();
        // when(repo.addNewAttend(added)).thenReturn(la_repo);
        List<Attend> la_servAd = service.setNewAttend(added);
        assertEquals (la_servAd,la_repo);
        assertEquals(la_servAd, la_servGet);

        la_repo.add(added2);

        when(repo.getListOfAttend()).thenReturn(la_repo);
        List<Attend> la_servGet_2 = service.getAllAttend();
        // when(repo.addNewAttend(added2)).thenReturn(la_repo);
        List<Attend> la_servAd_2 = service.setNewAttend(added2);
        assertEquals (la_servAd_2,la_repo);
        assertEquals(la_servAd_2, la_servGet_2);
    }
    @Test
    void setNewAttend_null()  {
        Attend added = a1;
        Attend added2 = null;

        List<Attend> la_repo = new ArrayList<>();
        la_repo.add(added);

        when(repo.addNewAttend(added)).thenReturn(la_repo);
        List<Attend> la_servAd = service.setNewAttend(added);
        assertEquals (la_servAd,la_repo);

        when(repo.getListOfAttend()).thenReturn(la_repo);
        List<Attend> la_servGet = service.getAllAttend();
        assertEquals(la_servAd, la_servGet);

        when(repo.addNewAttend(added2)).thenReturn(la_repo);
        List<Attend> la_servAd_2 = service.setNewAttend(added2);
        assertEquals (la_servAd_2,la_repo);

        when(repo.getListOfAttend()).thenReturn(la_repo);
        List<Attend> la_servGet_2 = service.getAllAttend();
        assertEquals(la_servAd_2, la_servGet_2);
    }
    @Test
    void setNewAttend_noneDate()  {
        Attend added = a1;
        Attend no_added = a_noneDatetime;
        List<Attend> la_repo = new ArrayList<>();
        la_repo.add(added);

        when(repo.addNewAttend(no_added)).thenReturn(la_repo);
        List<Attend> la_servAd = service.setNewAttend(no_added);
        assertEquals (la_servAd,la_repo);

        when(repo.getListOfAttend()).thenReturn(la_repo);
        List<Attend> la_servGet = service.getAllAttend();
        assertEquals(la_servAd, la_servGet);
    }*/
    @Test
    void setNewAttend_noneDate_noAttend()  {
/*        Attend added = a_noneAttend_noneDatetime;
        repo.addNewAttend(a1);
        repo.addNewAttend(added);
        List<Attend> la = repo.getListOfAttend();
        assertEquals (la.get(0) , a1);
        assertEquals (la.size() , 1);*/
    }
    @Test
    void setNewAttend_noneAttend()  {
/*        Attend added = a_noneAttend;
        repo.addNewAttend(added);
        List<Attend> la = repo.getListOfAttend();
        Attend add = la.get(0);
        assert (
                add.getId() == a_noneAttend.getId() &
                        Objects.equals(add.getDatetime(), a_noneAttend.getDatetime()) &
                add.getStudent() == a_noneAttend.getStudent() &
                add.getSubject() == a_noneAttend.getSubject() &
                        Objects.equals(add.getAttended(), "-")
        );
        assertEquals (la.size() , 1);*/
    }
    //endregion


}
