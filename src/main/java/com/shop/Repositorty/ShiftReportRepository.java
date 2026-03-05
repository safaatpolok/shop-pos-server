package com.shop.Repositorty;

import com.shop.modal.ShiftReport;
import com.shop.modal.User;
import com.shop.payload.dto.ShiftReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShiftReportRepository extends JpaRepository<ShiftReport, Long> {

    List<ShiftReport> findByCashierId(Long id);
    List<ShiftReport> findByBranchId(Long id);

    Optional<ShiftReport>findTopByCashierAndShiftEndIsNullOrderByShiftEndDesc(User cashierId);


    Optional<ShiftReport> findByCashierAndShiftStartBetween(User cashier,
                                                            LocalDateTime start,
                                                            LocalDateTime end);

}
