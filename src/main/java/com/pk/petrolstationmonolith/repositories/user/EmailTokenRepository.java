package com.pk.petrolstationmonolith.repositories.user;

import com.pk.petrolstationmonolith.entities.user.EmailToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmailTokenRepository extends JpaRepository<EmailToken, Long> {

    Optional<EmailToken> findByToken(UUID token);

    boolean existsByToken(UUID token);

}
