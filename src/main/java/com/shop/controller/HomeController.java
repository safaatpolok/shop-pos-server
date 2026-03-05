package com.shop.controller;

import com.shop.payload.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<ApiResponse> home() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("welcome to shop pos");
        return  ResponseEntity.ok(apiResponse);
    }
}
