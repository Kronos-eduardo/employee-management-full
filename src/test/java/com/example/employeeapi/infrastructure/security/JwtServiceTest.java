package com.example.employeeapi.infrastructure.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

public class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secret",
                "YjY5YjAyN2JhMzA5ZWI2Y2RlZjgxYjZhOGEwOTU5YTM4MmY1ZDBmYjU3ZTM1ZDRlMDRiY2FkMGQ3MDcwZmI5YjJmYjY1YjAyN2JhMzA5ZWI2Y2RlZjgxYjZhOGEwOTU5YTM4MmY1ZDBmYjU3ZTM1ZDRlMDRiY2FkMGQ3MDcwZmI5YjI=");
        jwtService.init();
    }

    @Test
    void testGenerateToken() {
        String token = jwtService.generateToken("testuser");

        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    void testExtractUsername() {
        String username = "testuser";
        String token = jwtService.generateToken(username);

        String extractedUsername = jwtService.extractUsername(token);

        assertEquals(username, extractedUsername);
    }

    @Test
    void testIsTokenValid() {
        String token = jwtService.generateToken("testuser");

        boolean isValid = jwtService.isTokenValid(token);

        assertTrue(isValid);
    }

    @Test
    void testIsTokenInvalid() {
        boolean isValid = jwtService.isTokenValid("invalid.token.here");

        assertFalse(isValid);
    }
}

