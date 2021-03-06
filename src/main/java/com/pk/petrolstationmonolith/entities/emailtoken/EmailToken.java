package com.pk.petrolstationmonolith.entities.emailtoken;

import com.pk.petrolstationmonolith.entities.account.Customers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EmailToken")
public class EmailToken {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "Token")
    private UUID token;

    @Column(name = "CreationDateTime")
    private LocalDateTime creationDateTime;

    @ManyToOne
    private Customers customers;

    public EmailToken(UUID token, Customers customers) {
        this.token = token;
        this.customers = customers;
        creationDateTime = LocalDateTime.now();
    }

}
