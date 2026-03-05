package com.shop.mapper;

import com.shop.modal.Refund;
import com.shop.payload.dto.RefundDTO;

public class RefundMapper {
    public static RefundDTO toDTO(Refund refund) {

        return RefundDTO.builder()
                .id(refund.getId())
                .orderId(refund.getId())
                .reason(refund.getReason())
                .amount(refund.getAmount())
                .cashierName(refund.getCashier().getFullName())
                .branchId(refund.getBranch().getId())

                .shiftReportId(refund.getShiftReport()!=null?refund.getShiftReport().getId():null)

                .createdAt(refund.getCreatedAt())
                .build();
    }
}
