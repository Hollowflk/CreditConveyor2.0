package com.deal.Services;

import com.CreditConveyor.DTO.LoanOfferDTO;
import com.CreditConveyor.Enums.Gender;
import com.CreditConveyor.Enums.MaritalStatus;
import com.CreditConveyor.Enums.PositionAtWork;
import com.CreditConveyor.Enums.WorkStatus;
import com.deal.Entity.Application;
import com.deal.Entity.Client;
import com.deal.Entity.Employment;
import com.deal.Entity.Passport;
import com.deal.Repository.ApplicationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationServiceTest {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    @Transactional
    void save() {

        Application application = Application.builder()
                .client(Client.builder()
                        .last_name("Ivanov")
                        .first_name("Ivan")
                        .middle_name("Ivanovich")
                        .birth_date(LocalDate.of(1989,11,29))
                        .email("Ivan@mail.ru")
                        .gender(Gender.MAN)
                        .maritalStatus(MaritalStatus.SINGLE)
                        .dependentAmount(1)

                            .passport(Passport.builder()
                                .series("1234")
                                .number("123456")
                                .issueBranch("Moscow city")
                                .issueDate(LocalDate.of(2012,12,12))
                                .build())

                                .employment(Employment.builder()
                                    .employmentStatus(WorkStatus.EMPLOYED)
                                    .employer("123456")
                                    .salary(BigDecimal.valueOf(500000))
                                    .position(PositionAtWork.MIDDLE_MANAGER)
                                    .workExperienceTotal(20)
                                    .workExperienceCurrent(20)
                                    .build())

                        .account("123456")
                        .build())
                .build();

        applicationRepository.save(application);

        assertNotNull(application.getClient());
        assertNotNull(application.getClient().getPassport());
        assertNotNull(application.getClient().getEmployment());
    }

    @Test
    void findById() {
        Long id = 1L;
        Optional<Application> application = applicationRepository.findById(id);
        assertEquals(application, Optional.empty());
    }
}