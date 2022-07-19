package com.application.Services;

import com.CreditConveyor.DTO.LoanApplicationRequestDTO;
import com.CreditConveyor.DTO.LoanOfferDTO;
import com.application.Util.FeignClientApplication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationService {

    private final FeignClientApplication feignClientApplication;

    public List<LoanOfferDTO> createApplicationsOffers(LoanApplicationRequestDTO requestDTO) {

        log.info("Пришло requestDTO: {}", requestDTO);

        List<LoanOfferDTO> loanOfferDTOList = feignClientApplication.getLoanOffersDTOList(requestDTO);

        log.info("Созданы кредитные предложения: {}", loanOfferDTOList);

        return loanOfferDTOList;
    }

    public void acceptOffer(LoanOfferDTO loanOfferDTO) {

        log.info("Пришло loanOfferDTO: {}", loanOfferDTO);

        feignClientApplication.getLoanOffersDTO(loanOfferDTO);

        log.info("Кредитное предложение выбрано: {}", loanOfferDTO);

    }
}
