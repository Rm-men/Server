package com.example.demo;

import com.example.demo.operation.AttendOperationImpl;
import com.example.demo.service.endpoint.AttendService;
import com.example.demo.types.Attend;
import com.example.demo.types.Student;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AttendedControllerTest {
    @InjectMocks
    private AttendService attendService;
    @Mock
    private AttendOperationImpl attendOperation;

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
    void getAllAttend_empty(){
        List<Attend> la = attendOperation.getListOfAttend();
        assert (la.size() == 0);
    }
    @Test
    void getAllAttend_noEmpty(){
        List<Attend> la = attendOperation.getListOfAttend();
        assert (la.size() == 0);
        la = attendOperation.addNewAttend(a1);
        assert (la.size() == 1);
        assert (la.get(0) == a1);
    }
    //endregion

    //region NewAttend (6)
    @Test
    void setNewAttend_first(){
        Attend added = a1;
        attendOperation.addNewAttend(added);
        List<Attend> la = attendOperation.getListOfAttend();
        assert (la.get(0) == added);
    }
    @Test
    void setNewAttend_noFirst(){
        Attend added = a2;
        attendOperation.addNewAttend(a1);
        attendOperation.addNewAttend(added);
        List<Attend> la = attendOperation.getListOfAttend();
        assert (la.get(0) == a1);
        assert (la.get(1) == added);
    }
    @Test
    void setNewAttend_null(){
        Attend added = null;
        List<Attend> la;
        List<Attend> la1;
        List<Attend> la2 = null;
        la = attendOperation.addNewAttend(a1);
        la1 = attendOperation.addNewAttend(added);
        la2.add((Attend) la);
        la2.add((Attend) la1);
        assert (la2.size() == 1);

    }
    @Test
    void setNewAttend_noneDate(){
        Attend added = a_noneDate;
        attendOperation.addNewAttend(a1);
        attendOperation.addNewAttend(added);
        List<Attend> la = attendOperation.getListOfAttend();
        assert (la.get(0) == a1);
        assert (la.size() == 1);
    }
    @Test
    void setNewAttend_noneDate_noAttend(){
        Attend added = a_noneAttend_noneDate;
        attendOperation.addNewAttend(a1);
        attendOperation.addNewAttend(added);
        List<Attend> la = attendOperation.getListOfAttend();
        assert (la.get(0) == a1);
        assert (la.size() == 1);
    }
    @Test
    void setNewAttend_noneAttend(){
        Attend added = a_noneAttend;
        attendOperation.addNewAttend(added);
        List<Attend> la = attendOperation.getListOfAttend();
        Attend add = la.get(0);
        assert (
                add.getId() == a_noneAttend.getId() &
                        Objects.equals(add.getDatetime(), a_noneAttend.getDatetime()) &
                add.getStudent() == a_noneAttend.getStudent() &
                add.getSubject() == a_noneAttend.getSubject() &
                        Objects.equals(add.getAttended(), "-")
        );
        assert (la.size() == 1);
    }
    //endregion
}
