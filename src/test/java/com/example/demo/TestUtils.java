package com.example.demo;


import com.example.demo.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestUtils {

    @Test
    public void testGetConnectJDBC() {
        try {
            assertFalse(JDBCUtils.getConnectJDBC().isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
