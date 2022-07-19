package com.deal.Entity;

import com.CreditConveyor.DTO.PaymentScheduleElementDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "paymentSchedule")
public class PaymentSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;

    private LocalDate date;

    private BigDecimal totalPayment;

    private BigDecimal interestPayment;

    private BigDecimal debtPayment;

    private BigDecimal remainInDebt;

    public PaymentSchedule (PaymentScheduleElementDTO paymentScheduleElementDTO) {
        number = paymentScheduleElementDTO.getNumber();
        date = paymentScheduleElementDTO.getDate();
        totalPayment = paymentScheduleElementDTO.getTotalPayment();
        interestPayment = paymentScheduleElementDTO.getInterestPayment();
        debtPayment = paymentScheduleElementDTO.getDebtPayment();
        remainInDebt = paymentScheduleElementDTO.getRemainInDebt();
    }

}
