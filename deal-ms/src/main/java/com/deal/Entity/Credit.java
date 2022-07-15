package com.deal.Entity;

import com.CreditConveyor.DTO.CreditDTO;
import com.deal.Enums.CreditStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "credit")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal amount;

    private Integer term;

    private BigDecimal monthly_payment;

    private BigDecimal rate;

    private BigDecimal psk;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_id")
    private List<PaymentSchedule> payment_schedule;

    private Boolean is_insurance_enabled;

    private Boolean is_salary_client;

    @Enumerated(EnumType.STRING)
    private CreditStatus credit_status;

    public Credit (CreditDTO creditDTO) {
        amount = creditDTO.getAmount();
        term = creditDTO.getTerm();
        monthly_payment = creditDTO.getMonthlyPayment();
        rate = creditDTO.getRate();
        psk = creditDTO.getPsk();
        List<PaymentSchedule> schedules = creditDTO.getPaymentSchedule()
                .stream()
                .map(PaymentSchedule::new)
                .collect(Collectors.toList());
        payment_schedule = schedules;
        is_insurance_enabled = creditDTO.getIsInsuranceEnabled();
        is_salary_client = creditDTO.getIsSalaryClient();
    }
}
