package com.deal.Controllers;

import com.CreditConveyor.DTO.FinishRegistrationRequestDTO;
import com.CreditConveyor.DTO.LoanApplicationRequestDTO;
import com.CreditConveyor.DTO.LoanOfferDTO;
import com.deal.Services.DealService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deal")
@Tag(name = "Сервис сделка")
public class DealController {

    private final DealService dealService;

    @PostMapping("/application")
    @Operation(summary = "Расчёт возможных условий кредита")
    public List<LoanOfferDTO> crateLoanOffersList (@RequestBody LoanApplicationRequestDTO requestDTO) {
        return dealService.createApplication(requestDTO);
    }

    @PutMapping("/offer")
    @Operation(summary = "Выбор одного из предложений")
    public void acceptOffer(@RequestBody LoanOfferDTO loanOfferDTO) {
        dealService.applicationOffer(loanOfferDTO);
    }

    @PutMapping("/calculate/{id}")
    @Operation(summary = "Завершение регистрации")
    public void calculation (@RequestBody FinishRegistrationRequestDTO finishRegistrationRequestDTO,@PathVariable Long id) {
        dealService.finishRegistration(finishRegistrationRequestDTO,id);

    }
}
