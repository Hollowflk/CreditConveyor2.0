package com.application.Controllers;

import com.CreditConveyor.DTO.LoanApplicationRequestDTO;
import com.CreditConveyor.DTO.LoanOfferDTO;
import com.application.Services.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/application")
@Tag(name = "Сервис заяка")
public class ApplicationController {

    private ApplicationService applicationService;

    @PostMapping("/application")
    @Operation(summary = "Расчёт возможных условий кредита")
    List<LoanOfferDTO> createApplication(@RequestBody LoanApplicationRequestDTO requestDTO) {
        return applicationService.createApplicationsOffers(requestDTO);
    }

    @PutMapping("/offer")
    @Operation(summary = "Выбор одного из предложений")
    public void createOffer(@RequestBody LoanOfferDTO loanOfferDTO) {
        applicationService.acceptOffer(loanOfferDTO);
    }
}
