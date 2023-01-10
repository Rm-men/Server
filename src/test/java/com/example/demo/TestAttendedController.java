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
import java.sql.SQLException;
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

    Attend a1 = new Attend(3, 0, new Date().toString(), 0, "-");

    public TestAttendedController() throws SQLException {
    }

    //region GetAttends (1)
    @Test
    void getAllAttend() {
        List<Attend> la = new ArrayList<>();
        when(repo.getListOfAttend()).thenReturn(la);
        List<Attend> la2 = service.getAllAttend();
        assertEquals (la2 , la);
    }

    //endregion
    //region NewAttend (1)
/*    @Test
    void setNewAttend()  {
        Attend added = a1;
        List<Attend> la_repo = new ArrayList<>();
        when(repo.getListOfAttend()).thenReturn(la_repo);
        // Изначально репо пустой
        List<Attend> la_servGet_onNuls = service.getAllAttend();
        assertEquals(la_servGet_onNuls.size(), 0);
        // Добавляется атенд
        la_repo.add(added);
        assertNotNull(service.setNewAttend(added));
    }*/
    //endregion

}
