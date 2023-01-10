package com.example.demo.service.endpoint;

import com.example.demo.operation.AttendOperation_repo;
import com.example.demo.operation.AttendOperation_repo_Impl;
import com.example.demo.types.Attend;
import com.example.demo.utils.JDBCUtils;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.sql.SQLException;
import java.util.List;

@WebService()
public class AttendService {

    private final AttendOperation_repo repo;

    public AttendService() throws SQLException {
        repo = new AttendOperation_repo_Impl();
        repo.setConn(JDBCUtils.getConnectJDBC());
    }
    public AttendService(AttendOperation_repo ao_r) throws SQLException {
        repo = ao_r;
        repo.setConn(JDBCUtils.getConnectJDBC());

    }

    @WebMethod()
    public List<Attend> getAllAttend()  {
        return repo.getListOfAttend();
    }
/*    @WebMethod()
    public List<Attend> setNewAttend(Attend a)  {
        return repo.addNewAttend(a);
    }*/
}
