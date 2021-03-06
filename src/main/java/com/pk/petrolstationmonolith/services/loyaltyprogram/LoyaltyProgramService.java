package com.pk.petrolstationmonolith.services.loyaltyprogram;

import com.pk.petrolstationmonolith.entities.loyaltyprogram.LoyaltyProgram;
import com.pk.petrolstationmonolith.enums.ServiceType;
import com.pk.petrolstationmonolith.exceptions.loyaltyprogram.NotEnoughLoyaltyProgramPointsException;
import com.pk.petrolstationmonolith.exceptions.loyaltyprogram.UserHasAlreadyJoinedTheLoyaltyProgramException;
import com.pk.petrolstationmonolith.exceptions.loyaltyprogram.UserHasNotJoinedTheLoyaltyProgramException;
import com.pk.petrolstationmonolith.models.loyaltyprogram.Points;
import com.pk.petrolstationmonolith.models.loyaltyprogram.ProgramParameters;
import com.pk.petrolstationmonolith.models.loyaltyprogram.RequestChangeProgramParameters;
import com.pk.petrolstationmonolith.repositories.loyaltyprogram.LoyaltyProgramRepository;
import com.pk.petrolstationmonolith.services.account.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class LoyaltyProgramService {

    private final LoyaltyProgramRepository loyaltyProgramRepository;
    private final UserService userService;

    public Points joinLoyaltyProgram(long userId) {
        if (loyaltyProgramRepository.findByUserId(userId).isPresent()) {
            throw new UserHasAlreadyJoinedTheLoyaltyProgramException(userId);
        }
        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        loyaltyProgram.setCustomers(userService.getUser(userId));
        log.trace("User with id " + userId + " joined loyalty program");
        return new Points(loyaltyProgramRepository.save(loyaltyProgram).getPoints());
    }

    public Points getPoints(long userId) {
        log.trace("Getting points for user with id " + userId);
        return new Points(getLoyaltyProgram(userId).getPoints());
    }

    public Points addProgramPoints(long userId, ServiceType serviceType, Points points) {
        LoyaltyProgram loyaltyProgram = getLoyaltyProgram(userId);

        long additionalPoints = serviceType.getPoints() * points.getPoints();
        log.trace("Adding " + additionalPoints + " points to user with id " + userId);
        loyaltyProgram.setPoints(loyaltyProgram.getPoints() + additionalPoints);

        return new Points(loyaltyProgramRepository.save(loyaltyProgram).getPoints());
    }

    public void addProgramPointsAfterTransaction(long userId, ServiceType serviceType, int number) {
        Optional<LoyaltyProgram> optionalLoyaltyProgram = loyaltyProgramRepository.findByUserId(userId);

        if (optionalLoyaltyProgram.isEmpty()) {
            return;
        }

        LoyaltyProgram loyaltyProgram = optionalLoyaltyProgram.get();

        long points = serviceType.getPoints() * number;
        log.trace("Adding " + points + " points to user with id " + userId + " after transaction");
        loyaltyProgram.setPoints(loyaltyProgram.getPoints() + points);

        loyaltyProgramRepository.save(loyaltyProgram);
    }

    public Points exchangePoints(long userId, ServiceType serviceType, Points points) {
        LoyaltyProgram loyaltyProgram = getLoyaltyProgram(userId);

        long exchangingPoints = serviceType.getCost() * points.getPoints();

        if (loyaltyProgram.getPoints() - exchangingPoints < 0) {
            throw new NotEnoughLoyaltyProgramPointsException(userId);
        }
        log.trace("Exchanging " + exchangingPoints + " points for service " + serviceType + " with amount " + points.getPoints());
        loyaltyProgram.setPoints(loyaltyProgram.getPoints() - exchangingPoints);

        return new Points(loyaltyProgramRepository.save(loyaltyProgram).getPoints());
    }

    public ProgramParameters changeProgramParameters(ServiceType serviceType, RequestChangeProgramParameters request) {
        serviceType.setPoints(request.getPoints());
        serviceType.setCost(request.getCost());
        log.trace("Changing program parameters for service " + serviceType.toString());
        return new ProgramParameters();
    }

    public ProgramParameters getProgramParameters() {
        log.trace("Getting program parameters");
        return new ProgramParameters();
    }

    private LoyaltyProgram getLoyaltyProgram(Long userId) {
        return loyaltyProgramRepository.findByUserId(userId)
                .orElseThrow(() -> new UserHasNotJoinedTheLoyaltyProgramException(userId));
    }

}
