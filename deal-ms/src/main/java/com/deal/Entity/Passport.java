package com.deal.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "passport")
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passport_id")
    private Integer id;

    private String series;

    private String number;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "issue_branch")
    private String issueBranch;
}
