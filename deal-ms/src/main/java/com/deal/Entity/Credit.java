package com.deal.Entity;

import com.CreditConveyor.DTO.CreditDTO;
import com.deal.Enums.CreditStatus;
import lombok.*;

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
@Builder
@AllArgsConstructor
@Table(name = "credit")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_id")
    private Integer id;

    private BigDecimal amount;

    private Integer term;

    @Column(name = "monthly_payment")
    private BigDecimal monthlyPayment;

    private BigDecimal rate;

    private BigDecimal psk;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_id")
    @Column(name = "payment_schedule")
    private List<PaymentSchedule> paymentSchedule;

    @Column(name = "is_insurance_enabled")
    private Boolean isInsuranceEnabled;

    @Column(name = "is_salary_client")
    private Boolean isSalaryClient;

    @Enumerated(EnumType.STRING)
    @Column(name = "credit_status")
    private CreditStatus creditStatus;

    public Credit (CreditDTO creditDTO) {
        amount = creditDTO.getAmount();
        term = creditDTO.getTerm();
        monthlyPayment = creditDTO.getMonthlyPayment();
        rate = creditDTO.getRate();
        psk = creditDTO.getPsk();
        List<PaymentSchedule> schedules = creditDTO.getPaymentSchedule()
                .stream()
                .map(PaymentSchedule::new)
                .collect(Collectors.toList());
        paymentSchedule = schedules;
        isInsuranceEnabled = creditDTO.getIsInsuranceEnabled();
        isSalaryClient = creditDTO.getIsSalaryClient();
    }
}
