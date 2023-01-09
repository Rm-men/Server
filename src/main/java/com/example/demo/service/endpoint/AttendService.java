package com.example.demo.service.endpoint;

import com.example.demo.operation.AttendOperation_repo;
import com.example.demo.operation.AttendOperation_repo_Impl;
import com.example.demo.types.Attend;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService()
public class AttendService {

    private final AttendOperation_repo repo;

    public AttendService(){
        repo = new AttendOperation_repo_Impl();
    }
    public AttendService(AttendOperation_repo ao_r) {
        repo = ao_r;
    }

    @WebMethod()
    public List<Attend> getAllAttend()  {
        return repo.getListOfAttend();
    }
    @WebMethod()
    public List<Attend> setNewAttend(Attend a)  {
        return repo.addNewAttend(a);
    }
}
