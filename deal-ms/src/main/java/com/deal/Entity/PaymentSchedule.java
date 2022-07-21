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
@Table(name = "payment_schedule")
public class PaymentSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer id;

    private Integer number;

    private LocalDate date;

    @Column(name = "total_payment")
    private BigDecimal totalPayment;

    @Column(name = "interest_payment")
    private BigDecimal interestPayment;

    @Column(name = "debt_payment")
    private BigDecimal debtPayment;

    @Column(name = "remain_in_debt")
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
