package com.deal.Util;

import com.CreditConveyor.DTO.CreditDTO;
import com.CreditConveyor.DTO.LoanApplicationRequestDTO;
import com.CreditConveyor.DTO.LoanOfferDTO;
import com.CreditConveyor.DTO.ScoringDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "creditConveyor", url = "http://localhost:8080/conveyor")
public interface FeignClientService {

    @RequestMapping(method = RequestMethod.POST, value = "/offers")
    List<LoanOfferDTO> getLoanOffer (@RequestBody LoanApplicationRequestDTO requestDTO);

    @RequestMapping(method = RequestMethod.POST, value = "/calculation")
    CreditDTO getCredit (@RequestBody ScoringDataDTO scoringDataDTO);
}
