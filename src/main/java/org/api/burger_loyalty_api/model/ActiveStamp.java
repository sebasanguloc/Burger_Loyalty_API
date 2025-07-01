package org.api.burger_loyalty_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "active_stamps", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "enable_dt"})
})
public class ActiveStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @CreationTimestamp
    @Column(name = "enable_dt")
    private LocalDateTime enableDt;

    @NotNull
    @Column(name = "expiration_dt")
    private LocalDateTime expirationDt;
}
