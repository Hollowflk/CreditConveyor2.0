package com.deal.Entity;

import com.CreditConveyor.DTO.LoanOfferDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loan_offer")
public class LoanOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_offer_id")
    private Integer id;

    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "requested_amount")
    private BigDecimal requestedAmount;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    private Integer term;

    @Column(name = "monthly_payment")
    private BigDecimal monthlyPayment;

    private BigDecimal rate;

    @Column(name = "is_insurance_enabled")
    private Boolean isInsuranceEnabled;

    @Column(name = "is_salary_client")
    private Boolean isSalaryClient;

    public LoanOffer(LoanOfferDTO loanOfferDTO) {
        applicationId = loanOfferDTO.getApplicationId();
        requestedAmount = loanOfferDTO.getRequestedAmount();
        totalAmount = loanOfferDTO.getTotalAmount();
        term = loanOfferDTO.getTerm();
        monthlyPayment = loanOfferDTO.getMonthlyPayment();
        rate = loanOfferDTO.getRate();
        isInsuranceEnabled = loanOfferDTO.getIsInsuranceEnabled();
        isSalaryClient = loanOfferDTO.getIsSalaryClient();
    }
}
