package com.deal.Entity;

import com.CreditConveyor.DTO.EmploymentDTO;
import com.CreditConveyor.Enums.PositionAtWork;
import com.CreditConveyor.Enums.WorkStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "employment")
public class Employment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employment_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "employment_status")
    private WorkStatus employmentStatus;

    private String employer;

    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    private PositionAtWork position;

    @Column(name = "work_experience_total")
    private Integer workExperienceTotal;

    @Column(name = "work_experience_current")
    private Integer workExperienceCurrent;

    public Employment(EmploymentDTO employmentDTO) {
        employmentStatus = employmentDTO.getEmploymentStatus();
        employer = employmentDTO.getEmployerINN();
        salary = employmentDTO.getSalary();
        position = employmentDTO.getPosition();
        workExperienceTotal = employmentDTO.getWorkExperienceTotal();
        workExperienceCurrent = employmentDTO.getWorkExperienceCurrent();

    }
}
