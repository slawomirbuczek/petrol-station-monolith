package com.pk.petrolstationmonolith.repositories.account;

import com.pk.petrolstationmonolith.entities.account.EmailToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmailTokenRepository extends JpaRepository<EmailToken, Long> {

    Optional<EmailToken> findByToken(UUID token);

    boolean existsByToken(UUID token);

    @Transactional
    void deleteByToken(UUID token);

    @Transactional
    void deleteAllByCreationDateTimeBefore(LocalDateTime creationDateTime);


}
