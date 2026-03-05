package com.shop.service;

import com.shop.Repositorty.*;
import com.shop.domain.UserRole;
import com.shop.modal.Order;
import com.shop.payload.storeAnalytics.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class StoreAnalyticsServiceImpl implements StoreAnalyticsService {


    private final BranchRepository branchRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final RefundRepository refundRepository;
    private final ProductRepository productRepository;


    @Override
    public StoreOverviewDTO getStoreOverview(Long storeAdminId) {
        List<UserRole> roles = new ArrayList<>();
        roles.add(UserRole.ROLE_BRANCH_MANAGER);
        roles.add(UserRole.ROLE_STORE_MANAGER);
        roles.add(UserRole.ROLE_BRANCH_CASHIER);
        roles.add(UserRole.ROLE_BRANCH_CASHIER);

        return StoreOverviewDTO.builder()
                .totalBranches(branchRepository.countByStoreAdinId(storeAdminId))
                .totalSales(orderRepository.sumTotalSalesByStoreAdmin(storeAdminId).orElse(
                        Double.valueOf(0)
                ))
                .totalOrders(orderRepository.countByStoreAdmin(storeAdminId))
                .totalEmployees(userRepository.countByStoreAdminIdAndRoles(storeAdminId, roles))
                .totalCustomers(customerRepository.countByStoreAdminId(storeAdminId))
                .totalCustomers(refundRepository.countByStoreAdminId(storeAdminId))
                .totalProducts(productRepository.countByStoreAdminId(storeAdminId))

                .build();
    }

    @Override
    public TimeSeriesDateDTO getSalesTrends(Long storeAdinId, String period) {
        return null;
    }

    @Override
    public List<TimeSeriesPointDTO> getMonthlySalesGraph(Long storeAdminId) {

        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start=end.minusDays(365);

                List<Order>orders=orderRepository.findAllByStoreAdminAndCreatedAtBetween(
                        storeAdminId,start,end
                );

        Map<YearMonth,Double> grouped=orders.stream()
                .collect(Collectors.groupingBy(
                        order->YearMonth.from(order.getCreatedAt()),
                        Collectors.summingDouble(order ->
                                order.getTotalAmount()!=null?
                                        order.getTotalAmount().doubleValue():0.0)
                ));
        return grouped.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry->new TimeSeriesPointDTO(
                        entry.getKey().atDay(1).atStartOfDay(),
                        entry.getValue()
                )).collect(Collectors.toList());

    }

    @Override
    public List<TimeSeriesPointDTO> getDailySalesGraph(Long storeAdminId) {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start=end.minusDays(6);


        return orderRepository.getDailySales(storeAdminId,start,end);
    }

    @Override
    public List<PaymentInsightDTO> getSalesByPaymentMethod(Long storeAdminId) {
        return orderRepository.getSalesByPaymentMethod(storeAdminId);
    }

    @Override
    public List<BranchSalesDTO> getSalesByBranch(Long storeAdminId) {
        return orderRepository.getSalesByBranch((storeAdminId));
    }

    @Override
    public List<PaymentInsightDTO> getPaymentBreakdown(Long storeAdminId) {
        return orderRepository.getSalesByPaymentMethod((storeAdminId));
    }

    @Override
    public BranchPerformanceDTO getBranchPerformance(Long storeAdminId) {
        return BranchPerformanceDTO.builder()
                .branchSales(orderRepository.getSalesByBranch((storeAdminId)))
                .newBranchesThisMonth(branchRepository.countNewBranchesThisMonth(storeAdminId))

                .build();
    }

    @Override
    public StoreAlertDTO getStoreAlerts(Long storeAdminId) {
        LocalDateTime sevenDaysAgo=LocalDateTime.now().minusDays(7);
        List <String> cashiers=userRepository.findInactiveCashiers(storeAdminId, sevenDaysAgo);
        return StoreAlertDTO.builder()
                .lowStockAlerts(productRepository.findLowStockProducts(storeAdminId))
                .noSalesToday(branchRepository.findBranchesWithNoSalesToday(storeAdminId))
                .refundSpikerAlerts(refundRepository.findRefundSpikes(storeAdminId))
                .inactiveCashiers(cashiers)
                .build();
    }
}
