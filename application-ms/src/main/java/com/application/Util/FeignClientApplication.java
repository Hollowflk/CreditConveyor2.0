package com.application.Util;

import com.CreditConveyor.DTO.LoanApplicationRequestDTO;
import com.CreditConveyor.DTO.LoanOfferDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "DealController", url = "${app.feign.config.url}")
public interface FeignClientApplication {

    @RequestMapping(value = "/application",method = RequestMethod.POST)
    List<LoanOfferDTO> getLoanOffersDTOList(@RequestBody LoanApplicationRequestDTO requestDTO);

    @RequestMapping(value = "/offer",method = RequestMethod.PUT)
    void getLoanOffersDTO(@RequestBody LoanOfferDTO loanOfferDTO);
}
