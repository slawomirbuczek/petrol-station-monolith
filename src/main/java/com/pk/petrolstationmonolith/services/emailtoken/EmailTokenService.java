package com.pk.petrolstationmonolith.services.emailtoken;

import com.pk.petrolstationmonolith.entities.emailtoken.EmailToken;
import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.exceptions.account.InvalidEmailTokenException;
import com.pk.petrolstationmonolith.repositories.account.EmailTokenRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class EmailTokenService {

    private final EmailTokenRepository emailTokenRepository;

    public EmailToken getByToken(String token) {
        log.trace("Getting token " + token);
        return emailTokenRepository.findByToken(getUUIDFromString(token))
                .orElseThrow(InvalidEmailTokenException::new);
    }

    public EmailToken createNewToken(User user) {
        UUID token;
        do {
            token = UUID.randomUUID();
        } while (emailTokenRepository.existsByToken(token));
        EmailToken emailToken = new EmailToken(token, user);
        log.trace("Created new emailToken " + emailToken.toString());
        return emailTokenRepository.save(emailToken);
    }

    public boolean tokenNotValid(String token, long userId) {
        log.trace("Validating token " + token + " with user " + userId);
        return getByToken(token).getUser().getId() != userId;
    }

    public void deleteToken(String token) {
        log.trace("Deleting token " + token);
        emailTokenRepository.deleteByToken(getUUIDFromString(token));
    }

    @Scheduled(fixedRate = 3_600_000)
    public void deleteInactiveTokens() {
        LocalDateTime yesterdayDateTime = LocalDateTime.now().minusDays(1);
        emailTokenRepository.deleteAllByCreationDateTimeBefore(yesterdayDateTime);
    }

    private UUID getUUIDFromString(String token) {
        try {
            return UUID.fromString(token);
        } catch (Exception e) {
            throw new InvalidEmailTokenException();
        }
    }

}
