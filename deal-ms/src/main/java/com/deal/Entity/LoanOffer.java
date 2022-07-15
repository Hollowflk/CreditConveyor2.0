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
@Table(name = "loanOffer")
public class LoanOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long applicationId;

    private BigDecimal requestedAmount;

    private BigDecimal totalAmount;

    private Integer term;

    private BigDecimal monthlyPayment;

    private BigDecimal rate;

    private Boolean isInsuranceEnabled;

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
