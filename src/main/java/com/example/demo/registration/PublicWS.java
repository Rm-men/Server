package com.example.demo.registration;

import com.example.demo.service.endpoint.AttendService;
import com.example.demo.service.endpoint.StudentService;
import com.example.demo.types.Attend;
import jakarta.xml.ws.Endpoint;

import java.sql.SQLException;

public class PublicWS {
    public static void main (String[] argv) throws SQLException {
        Endpoint.publish("http://localhost:8080/serv/AttendService",
                new AttendService());
        System.out.println("И это заработало");
        Endpoint.publish("http://localhost:8080/serv/StudentService",
                new StudentService());
        System.out.println("И то заработало");

    }
}
