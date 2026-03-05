package com.shop.Repositorty;

import com.shop.modal.Refund;
import com.shop.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundRepository  extends JpaRepository<Refund,Long> {


    List<Refund>findByCashierIdAndCreatedAtBetween(
            Long cashier,
            LocalDateTime from,
            LocalDateTime to
    );
    List<Refund>findByCashierId(Long id);
    List<Refund>findByShiftReportId(Long id);
    List<Refund> findByBranchId(Long id);

    @Query("""
            select count(r) from Refund r
            where r.order.branch.store.id=:storeAdminId
            
            """)
    int countByStoreAdminId(@Param("storeAdminId") Long storeAdmin);


    @Query("""
            select r.reason
            from Refund r
            where r.order.branch.store.id=:storeAdminId
            GROUP BY function('DATE',r.createdAt)
            having sum(r.amount)>5
            
            """)

    List<String> findRefundSpikes(@Param("storeAdminId") Long storeAdminId);



}
