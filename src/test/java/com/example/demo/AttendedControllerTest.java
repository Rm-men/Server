package com.example.demo;

import com.example.demo.operation.AttendOperationImpl;
import com.example.demo.service.endpoint.AttendService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class AttendedControllerTest {
    @InjectMocks
    private AttendService attendService;
    @Mock
    private AttendOperationImpl attendOperation;
    //region GetStudents
    @Test
    void getAllStudent_good(){

    }
    //endregion
    //region GetAttends
    @Test
    void getAllAttend_good(){

    }
    //endregion

    //region NewAttend
    @Test
    void setNewAttend_good(){

    }
    //endregion
}
