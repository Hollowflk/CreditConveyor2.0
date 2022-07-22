package com.deal.Entity;

import com.deal.Enums.ApplicationStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_id")
    private Credit credit;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @OneToOne(cascade = CascadeType.ALL)
    private LoanOffer appliedOffer;

    @Column(name = "sign_date")
    private LocalDate signDate;

    @Column(name = "ses_code")
    private String sesCode;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    @Column(name = "status_history")
    private List<ApplicationHistory> statusHistory;

    public Application(Client client) {
        this.client = client;
    }
}
