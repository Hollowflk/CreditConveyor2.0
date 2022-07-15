package com.deal.Entity;

import com.CreditConveyor.DTO.EmploymentDTO;
import com.CreditConveyor.Enums.PositionAtWork;
import com.CreditConveyor.Enums.WorkStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employment")
public class Employment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private WorkStatus employment_status;

    private String employer;

    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    private PositionAtWork position;

    private Integer work_experience_total;

    private Integer work_experience_current;

    public Employment(EmploymentDTO employmentDTO) {
        employment_status = employmentDTO.getEmploymentStatus();
        employer = employmentDTO.getEmployerINN();
        salary = employmentDTO.getSalary();
        position = employmentDTO.getPosition();
        work_experience_total = employmentDTO.getWorkExperienceTotal();
        work_experience_current = employmentDTO.getWorkExperienceCurrent();

    }
}
