package com.CreditConveyor.Controllers;


import com.CreditConveyor.DTO.CreditDTO;
import com.CreditConveyor.DTO.LoanApplicationRequestDTO;
import com.CreditConveyor.DTO.LoanOfferDTO;
import com.CreditConveyor.DTO.ScoringDataDTO;
import com.CreditConveyor.Services.CreditService;
import com.CreditConveyor.Services.LoanServiceOffers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/conveyor")
@Tag(name = "Кредитный конвейер", description = "Создает кредитные предложения и выдает кредит")
public class ConveyorController {

    private final LoanServiceOffers loanServiceOffers;
    private final CreditService creditService;

    @PostMapping("/offers")
    @Operation(summary = "Создание кредитного предложения")
    public List<LoanOfferDTO> calculationOfPossibleLoanConditions(@Valid @RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        return loanServiceOffers.createLoanOffersList(loanApplicationRequestDTO);
    }

    @PostMapping("/calculation")
    @Operation(summary = "Кредитный калькулятор")
    public CreditDTO createCredit(@Valid @RequestBody ScoringDataDTO scoringDataDTO){
        return creditService.createCredit(scoringDataDTO);
    }
}
