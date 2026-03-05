package com.shop.Repositorty;

import com.shop.domain.StoreStatus;
import com.shop.modal.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public interface StoreRepository extends JpaRepository<Store,Long> {

    Store findByStoreAdminId(Long adminId);
    List<Store>findByStatus(StoreStatus storeStatus);


    Long countByStatus(StoreStatus status);
    @Query("""
            
            SELECT count (s)
            from Store s
            where DATE(s.createdAt)=:data
            """)
    Long countByDate(LocalDate date);

    @Query("""
            SELECT s.createdAt as regDate,COUNT(s) as count
            
            from Store s
            where s.createdAt>=:startDate
            GROUP BY s.createdAt
            ORDER BY regDate DESC
            
            """)

    List<Object[]> getStoreRegistrationStates(
            @Param("stratData")LocalDateTime startDate
            );

}
