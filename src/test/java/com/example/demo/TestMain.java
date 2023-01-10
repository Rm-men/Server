package com.example.demo;


import com.example.demo.registration.PublicWS;
import com.example.demo.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestMain {

    @Test
    public void main() throws SQLException {
        PublicWS.main(new String[]{});
    }
}
