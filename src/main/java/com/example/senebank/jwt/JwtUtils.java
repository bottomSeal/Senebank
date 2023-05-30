package com.example.senebank.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.util.ObjectUtils;

public class JwtUtils {
    private JwtUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(ObjectUtils.isEmpty(header)){
            return null;
        }

        return header;
    }
}
