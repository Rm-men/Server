package com.example.demo.registration;

import com.example.demo.service.endpoint.AttendService;
import com.example.demo.service.endpoint.StudentService;
import jakarta.xml.ws.Endpoint;

import java.sql.SQLException;

public class PublicWS {
    public static void main (String[] argv) {
        Endpoint.publish("http://localhost:8080/serv/AttendService",
                new AttendService());
        Endpoint.publish("http://localhost:8080/serv/StudentService",
                new StudentService());
        System.out.print("И это заработало");
    }
}
