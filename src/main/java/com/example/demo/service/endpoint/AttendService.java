package com.example.demo.service.endpoint;

import com.example.demo.operation.AttendOperationImpl;
import com.example.demo.types.Attend;
import com.example.demo.types.Student;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.sql.SQLException;
import java.util.List;

@WebService()
public class AttendService {

    static AttendOperationImpl obj;

    static {
        try {
            obj = new AttendOperationImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @WebMethod()
    public List<Attend> getAllAttend() throws SQLException {
        return obj.getListOfAttend();
    }
    @WebMethod()
    public List<Attend> setNewAttend(Attend a) throws SQLException {
        return obj.addNewAttend(a);
    }
}
