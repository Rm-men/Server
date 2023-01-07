package com.example.demo.registration;

import com.example.demo.service.endpoint.AttendService;
import jakarta.xml.ws.Endpoint;

public class PublicWS {
    public static void main (String[] argv) {
        Endpoint.publish("http://localhost:8080/ws02/TovarService",
                new AttendService());
        System.out.print("И это заработало");
    }
}
