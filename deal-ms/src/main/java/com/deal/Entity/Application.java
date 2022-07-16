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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_id")
    private Credit credit;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private LocalDate creation_date;

    @OneToOne(cascade = CascadeType.ALL)
    private LoanOffer appliedOffer;

    private LocalDate sign_date;

    private String ses_code;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    private List<ApplicationHistory> status_history;

    public Application(Client client) {
        this.client = client;
    }
}
