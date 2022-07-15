package com.deal.Services;

import com.CreditConveyor.DTO.LoanApplicationRequestDTO;
import com.CreditConveyor.DTO.LoanOfferDTO;
import com.deal.Entity.*;
import com.deal.Enums.ApplicationStatus;
import com.deal.Util.FeignClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DealService {

    private final ClientService clientService;

    private final ApplicationService applicationService;

    private final FeignClientService feignClientService;

    public List<LoanOfferDTO> createApplication(LoanApplicationRequestDTO requestDTO) {

        log.info("Create application");

        Client client = createClient(requestDTO);

        List<LoanOfferDTO> loanOfferDTOList = feignClientService.getLoanOffer(requestDTO);

        for (LoanOfferDTO loanOfferDTO: loanOfferDTOList){

            Application application = new Application(client);

            application.setCreation_date(LocalDate.now());

            application.setStatus(ApplicationStatus.PREAPPROVAL);

            log.info("Applications created");

            applicationService.save(application);

            log.info("Application saved");

            loanOfferDTO.setApplicationId(application.getId());
        }

        log.info("Created loanOffersList");

        return loanOfferDTOList;

    }

    private Client createClient (LoanApplicationRequestDTO requestDTO) {

        log.info("Create client");

        Client client = Client.builder()
                .last_name(requestDTO.getLastName())
                .first_name(requestDTO.getFirstName())
                .middle_name(requestDTO.getMiddleName())
                .birth_date(requestDTO.getBirthdate())
                .email(requestDTO.getEmail())
                .build();

        log.info("Client created");

        clientService.save(client);

        log.info("Client saved");

        return client;
    }


    public void applicationOffer(LoanOfferDTO loanOfferDTO) {

        Application application = applicationService.findById(loanOfferDTO.getApplicationId());

        log.info("Выбрана заявка: " + application.getId());

        application.setStatus(ApplicationStatus.APPROVED);

        log.info("У заявки {} изменен статус на {}", application.getId(), application.getStatus());

        application.setStatus_history(createApplicationHistory(application));

        application.setAppliedOffer(new LoanOffer(loanOfferDTO));

        applicationService.save(application);

        log.info("Заявка сохоаннена в бд");
    }

    private List<ApplicationHistory> createApplicationHistory (Application application){

        ApplicationHistory applicationHistory = ApplicationHistory.builder()
                .id(application.getId())
                .status(application.getStatus())
                .time(LocalDateTime.now())
                .application(application)
                .build();

        List<ApplicationHistory> applicationHistories = new ArrayList<>(
                List.of(applicationHistory)
        );

        log.info("Созданна история изменений");

        return applicationHistories;
    }
}
