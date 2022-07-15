package com.CreditConveyor.DTO;

import com.CreditConveyor.Enums.Gender;
import com.CreditConveyor.Enums.MaritalStatus;

import java.time.LocalDate;

public class FinishRegistrationRequestDTO {

    private Gender gender;

    private MaritalStatus maritalStatus;

    private Integer dependentAmount;

    private LocalDate passportIssueDate;

    private String passportIssueBranch;

    private EmploymentDTO employment;

    private String account;
}
