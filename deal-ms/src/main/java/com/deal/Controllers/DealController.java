package com.deal.Controllers;

import com.CreditConveyor.DTO.LoanApplicationRequestDTO;
import com.CreditConveyor.DTO.LoanOfferDTO;
import com.deal.Services.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deal")
public class DealController {

    private final DealService dealService;

    @PostMapping("/application")
    public List<LoanOfferDTO> crateLoanOffersList (@RequestBody LoanApplicationRequestDTO requestDTO) {
        return dealService.createApplication(requestDTO);
    }

    @PutMapping("/offer")
    public void acceptOffer(@RequestBody LoanOfferDTO loanOfferDTO) {
        dealService.applicationOffer(loanOfferDTO);
    }


}
