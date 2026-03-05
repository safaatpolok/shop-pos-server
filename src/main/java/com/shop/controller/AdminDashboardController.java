package com.shop.controller;

import com.shop.payload.adminAnalytics.DashboardSummaryDTO;
import com.shop.payload.adminAnalytics.StoreRegistrationStateDTO;
import com.shop.payload.adminAnalytics.StoreStatusDistributionDTO;
import com.shop.service.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/super-admin")

public class AdminDashboardController {
    private final AdminDashboardService adminDashboardService;


    @GetMapping("/dashboard/summary")
        public ResponseEntity<DashboardSummaryDTO>getDashboardSummary(){
            return ResponseEntity.ok(adminDashboardService.getDashboardSummary()) ;

        }

    @GetMapping("/dashboard/store-registrations")
    public ResponseEntity<List<StoreRegistrationStateDTO>> getLast7DayRegistrationState(){
        return ResponseEntity.ok(adminDashboardService.getLast7DayRegistrationState()) ;

    }

    @GetMapping("/dashboard/store-status-distribution ")
    public ResponseEntity<StoreStatusDistributionDTO>getStoreStatusDistribution(){
        return ResponseEntity.ok(adminDashboardService.getStoreStatusDistribution()) ;

    }

    }

