package com.pk.petrolstationmonolith.entities.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "email_token")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailToken {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID token;

    private LocalDateTime creationDateTime;

    @OneToOne
    private User user;


    public EmailToken(User user) {
        this.user = user;
        creationDateTime = LocalDateTime.now();
    }

    public EmailToken(UUID token, User user) {
        this.token = token;
        this.user = user;
        creationDateTime = LocalDateTime.now();
    }

}
