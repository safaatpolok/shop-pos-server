package com.shop.service.impl;

import com.shop.Repositorty.StoreRepository;
import com.shop.domain.StoreStatus;
import com.shop.payload.adminAnalytics.DashboardSummaryDTO;
import com.shop.payload.adminAnalytics.StoreRegistrationStateDTO;
import com.shop.payload.adminAnalytics.StoreStatusDistributionDTO;
import com.shop.service.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminDashboardServiceImpl implements AdminDashboardService {

    private final StoreRepository storeRepository;

    @Override
    public DashboardSummaryDTO getDashboardSummary() {
        Long total = storeRepository.count();
        Long active=storeRepository.countByStatus(StoreStatus.ACTIVE);
        Long pending=storeRepository.countByStatus(StoreStatus.PENDING);
        Long blocked=storeRepository.countByStatus(StoreStatus.BLOCKED);
        return DashboardSummaryDTO.builder()
                .totalStore(total)
                .activeStore(active)
                .pendingStore(pending)
                .blockedStore(blocked)
                .build();

    }

    @Override
    public List<StoreRegistrationStateDTO> getLast7DayRegistrationState() {
        LocalDateTime today=LocalDateTime.now();
        LocalDateTime sevenDaysAgo=today.minusDays(6);

        List<Object[]>rawStates=storeRepository.getStoreRegistrationStates(sevenDaysAgo);
        Map<String, Long> dateMap=new LinkedHashMap<>();
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for(int i=0;i<7;i++){

            LocalDateTime date=sevenDaysAgo.plusDays(i);
            dateMap.put(date.format(dateTimeFormatter),0L);



        }
        for(Object[] row : rawStates){

            LocalDateTime date=(LocalDateTime)row[0];
            Long count = (Long) row[1];
            dateMap.put(date.format(dateTimeFormatter),count);

        }

        List<StoreRegistrationStateDTO> result=new ArrayList<>();
        dateMap.forEach((date,count)->result.add(
                StoreRegistrationStateDTO.builder().date(date).count(count).build()
        ));
        return result;
    }

    @Override
    public StoreStatusDistributionDTO getStoreStatusDistribution() {

        Long active=storeRepository.countByStatus(StoreStatus.ACTIVE);
        Long pending=storeRepository.countByStatus(StoreStatus.PENDING);
        Long blocked=storeRepository.countByStatus(StoreStatus.BLOCKED);
        return StoreStatusDistributionDTO.builder()
                .active(active)
                .pending(pending)
                .blocked(blocked)
                .build() ;
    }
}
