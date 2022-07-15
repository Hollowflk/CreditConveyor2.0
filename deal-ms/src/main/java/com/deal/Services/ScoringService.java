package com.deal.Services;

import com.CreditConveyor.DTO.FinishRegistrationRequestDTO;
import com.CreditConveyor.DTO.ScoringDataDTO;
import com.deal.Entity.Application;
import com.deal.Entity.Employment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ScoringService {

    public ScoringDataDTO returnScoringData (Application application, FinishRegistrationRequestDTO requestDTO) {

        ScoringDataDTO scoringDataDTO = checkScoringData(application, requestDTO);

        updateApplication(application, requestDTO);

        return scoringDataDTO;
    }

    private ScoringDataDTO checkScoringData (Application application, FinishRegistrationRequestDTO requestDTO) {

        log.info("Начало проверки данных");

        ScoringDataDTO scoringDataDTO = ScoringDataDTO.builder()
                .amount(application.getAppliedOffer().getRequestedAmount())
                .term(application.getAppliedOffer().getTerm())
                .firstName(application.getClient().getFirst_name())
                .lastName(application.getClient().getLast_name())
                .middleName(application.getClient().getMiddle_name())
                .gender(requestDTO.getGender())
                .birthdate(application.getClient().getBirth_date())
                .passportSeries(application.getClient().getPassport().getSeries())
                .passportNumber(application.getClient().getPassport().getNumber())
                .passportIssueBranch(requestDTO.getPassportIssueBranch())
                .passportIssueDate(requestDTO.getPassportIssueDate())
                .maritalStatus(requestDTO.getMaritalStatus())
                .dependentAmount(requestDTO.getDependentAmount())
                .employment(requestDTO.getEmployment())
                .account(requestDTO.getAccount())
                .isInsuranceEnabled(application.getAppliedOffer().getIsInsuranceEnabled())
                .isSalaryClient(application.getAppliedOffer().getIsSalaryClient())
                .build();

        log.info("Проверка пройдена");

        return scoringDataDTO;
    }

    @Transactional
    private void updateApplication (Application application, FinishRegistrationRequestDTO requestDTO) {

        log.info("Обновление данных в заявке №{}", application.getId());

        application.getClient().setGender(requestDTO.getGender());
        application.getClient().setMarital_status(requestDTO.getMaritalStatus());
        application.getClient().setDependent_amount(requestDTO.getDependentAmount());
        application.getClient().setEmployment(new Employment(requestDTO.getEmployment()));
        application.getClient().setAccount(requestDTO.getAccount());
        application.getClient().getPassport().setIssue_branch(requestDTO.getPassportIssueBranch());
        application.getClient().getPassport().setIssue_date(requestDTO.getPassportIssueDate());

        log.info("Данные успешно обновлены");
    }
}
