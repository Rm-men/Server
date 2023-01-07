package com.example.demo.service.endpoint;

import com.example.demo.operation.AttendOperationImpl;
import com.example.demo.types.Attend;
import com.example.demo.types.Student;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService()
public class AttendService {

    static AttendOperationImpl obj = new AttendOperationImpl();

    @WebMethod()
    public List<Attend> getAllAttend(){
        return obj.getListOfAttend();
    }
    @WebMethod()
    public List<Attend> setNewAttend(Attend a){
        return obj.addNewAttend(a);
    }
}
