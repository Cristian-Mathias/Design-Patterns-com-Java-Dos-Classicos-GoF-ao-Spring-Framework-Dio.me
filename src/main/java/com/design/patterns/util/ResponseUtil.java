package com.design.patterns.util;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    public static Map<String,Object> createResponse(String message, HttpStatus status){
        Map<String,Object>responseBody = new HashMap<>();
        responseBody.put("message",message);
        responseBody.put("status", status.value());
        responseBody.put("error", status.getReasonPhrase());
        responseBody.put("timestamp", LocalDateTime.now());
        return responseBody;
    }
}
