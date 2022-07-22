package com.deal.Entity;

import com.deal.Enums.ApplicationStatus;
import com.deal.Enums.ChangeType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "application_history")
public class ApplicationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_history_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private LocalDateTime time;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "application_id")
    private Application application;

    public ApplicationHistory(Application application) {
        status = application.getStatus();
        time = LocalDateTime.now();
        this.application = application;
    }
}
