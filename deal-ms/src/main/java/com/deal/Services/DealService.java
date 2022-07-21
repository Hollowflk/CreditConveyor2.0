package com.deal.Services;

import com.CreditConveyor.DTO.*;
import com.deal.Entity.*;
import com.deal.Enums.ApplicationStatus;
import com.deal.Util.FeignClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DealService {

    private final ClientService clientService;

    private final ApplicationService applicationService;

    private final FeignClientService feignClientService;

    private final ScoringService scoringService;

    @Transactional
    public List<LoanOfferDTO> createApplication(LoanApplicationRequestDTO requestDTO) {

        log.info("Create application");

        Client client = createClient(requestDTO);

        List<LoanOfferDTO> loanOfferDTOList = feignClientService.getLoanOffer(requestDTO);

        for (LoanOfferDTO loanOfferDTO: loanOfferDTOList){

            Application application = new Application(client);

            application.setCreationDate(LocalDate.now());

            application.setStatus(ApplicationStatus.PREAPPROVAL);

            log.info("Applications created");

            applicationService.save(application);

            log.info("Application saved");

            loanOfferDTO.setApplicationId(application.getId());
        }

        log.info("Created loanOffersList");

        return loanOfferDTOList;

    }

    @Transactional
    private Client createClient (LoanApplicationRequestDTO requestDTO) {

        log.info("Create client");

        Client client = Client.builder()
                .lastName(requestDTO.getLastName())
                .firstName(requestDTO.getFirstName())
                .middleName(requestDTO.getMiddleName())
                .birthDate(requestDTO.getBirthdate())
                .email(requestDTO.getEmail())
                .passport(createPassport(requestDTO))
                .build();

        log.info("Client created");

        clientService.save(client);

        log.info("Client saved");

        return client;
    }


    @Transactional
    public void applicationOffer(LoanOfferDTO loanOfferDTO) {

        Application application = applicationService.findById(loanOfferDTO.getApplicationId());

        application.setSignDate(LocalDate.now());

        log.info("Выбрана заявка: " + application.getId());

        application.setStatus(ApplicationStatus.APPROVED);

        log.info("У заявки {} изменен статус на {}", application.getId(), application.getStatus());

        application.setStatusHistory(createApplicationHistory(application));

        application.setAppliedOffer(new LoanOffer(loanOfferDTO));

        applicationService.save(application);

        log.info("Заявка сохраннена в бд");
    }

    @Transactional
    private List<ApplicationHistory> createApplicationHistory (Application application){

        if (application.getStatus() != null){
            if (application.getStatusHistory() == null) {
                List<ApplicationHistory> applicationHistories = new ArrayList<>();
            }
            application.getStatusHistory().add(new ApplicationHistory(application));
        }

        return application.getStatusHistory();
    }

    @Transactional
    public void finishRegistration(FinishRegistrationRequestDTO requestDTO, Long id){

        log.info("Начинается финальная регистрация");

        Application application = applicationService.findById(id);

        log.info("Выбрана заявка №{}", application.getId());

        ScoringDataDTO scoringDataDTO = scoringService.returnScoringData(application, requestDTO);

        log.info("Создание кредита");

        CreditDTO credit = feignClientService.getCredit(scoringDataDTO);

        application.setCredit(new Credit(credit));
        application.setStatus(ApplicationStatus.CREDIT_ISSUED);
        application.setStatusHistory(createApplicationHistory(application));

        log.info("Кредит создан");

    }

    @Transactional
    private Passport createPassport(LoanApplicationRequestDTO requestDTO){

        Passport passport = Passport.builder()
                .number(requestDTO.getPassportNumber())
                .series(requestDTO.getPassportSeries())
                .build();

        return passport;
    }


}
