package com.CreditConveyor.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PaymentScheduleElementDTO {

    private Integer number;

    private LocalDate date;

    private BigDecimal totalPayment;

    private BigDecimal interestPayment;

    private BigDecimal debtPayment;

    private BigDecimal remainInDebt;
}
