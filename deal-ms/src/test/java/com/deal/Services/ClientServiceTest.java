package com.deal.Services;

import com.CreditConveyor.DTO.LoanApplicationRequestDTO;
import com.deal.Entity.Application;
import com.deal.Entity.Client;
import com.deal.Entity.Passport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ApplicationService applicationService;

    @Test
    void save() {
        LoanApplicationRequestDTO requestDTO = LoanApplicationRequestDTO.builder()
                .amount(BigDecimal.valueOf(300000))
                .term(18)
                .firstName("Ivan")
                .lastName("Ivanov")
                .middleName("Ivanovich")
                .email("Ivan@mail.ru")
                .birthdate(LocalDate.of(1989,12,12))
                .passportSeries("1234")
                .passportNumber("123456")
                .build();

        Client client = Client.builder()
                .last_name(requestDTO.getLastName())
                .first_name(requestDTO.getFirstName())
                .middle_name(requestDTO.getMiddleName())
                .birth_date(requestDTO.getBirthdate())
                .passport(Passport.builder()
                        .series(requestDTO.getPassportSeries())
                        .series(requestDTO.getPassportSeries())
                        .build())
                .build();

        Application application = new Application(client);
        clientService.save(client);
        applicationService.save(application);
        Assertions.assertNotNull(application.getClient());

    }
}