package com.shop.mapper;

import com.shop.modal.Order;
import com.shop.modal.Product;
import com.shop.modal.Refund;
import com.shop.modal.ShiftReport;
import com.shop.payload.dto.OrderDTO;
import com.shop.payload.dto.ProductDTO;
import com.shop.payload.dto.RefundDTO;
import com.shop.payload.dto.ShiftReportDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ShiftReportMapper {

    public static ShiftReportDTO toDTO(ShiftReport entity){

        return ShiftReportDTO.builder()
                .id(entity.getId())
                .shiftEnd(entity.getShiftEnd())
                .shiftStart(entity.getShiftStart())
                .totalSales(entity.getTotalSales())
                .totalOrders(entity.getTotalOrders())
                .totalRefund(entity.getTotalRefund())
                .netSale(entity.getNetSale())
                .totalOrders(entity.getTotalOrders())
                .cashier(UserMapper.toDTO(entity.getCashier()))
                .branchId(entity.getBranch().getId())
                .recentOrders(mapOrders(entity.getRecentOrders()))
                .topSellingProducts(mapProducts(entity.getTopSellingProducts()))
                .refunds(mapRefunds(entity.getRefunds()))
                .paymentSummaries(entity.getPaymentSummaries())
                .build();
    }

    private static List<RefundDTO> mapRefunds(List<Refund> refunds) {

        if (refunds == null || refunds.isEmpty()) {return null;}
            return refunds.stream().map(RefundMapper::toDTO).collect(Collectors.toList());

    }

    private static List<ProductDTO> mapProducts(List<Product> topSellingProducts) {

        if (topSellingProducts == null || topSellingProducts.isEmpty()) {return null;}
        return topSellingProducts.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }

    private static List<OrderDTO> mapOrders(List<Order> recentOrders) {

        if (recentOrders == null || recentOrders.isEmpty()) {return null;}
        return recentOrders.stream().map(OrderMapper::toDTO).collect(Collectors.toList());
    }
}
