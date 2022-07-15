package com.deal.Entity;

import com.deal.Enums.ApplicationStatus;
import com.deal.Enums.ChangeType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "applicationHistory")
public class ApplicationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

}
