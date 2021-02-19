package com.pk.petrolstationmonolith.services.loyaltyprogram;

import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.entities.loyaltyprogram.LoyaltyProgram;
import com.pk.petrolstationmonolith.enums.pricelist.ServiceType;
import com.pk.petrolstationmonolith.exceptions.loyaltyprogram.NotEnoughLoyaltyProgramPointsException;
import com.pk.petrolstationmonolith.exceptions.loyaltyprogram.UserHasAlreadyJoinedTheLoyaltyProgramException;
import com.pk.petrolstationmonolith.exceptions.loyaltyprogram.UserHasNotJoinedTheLoyaltyProgramException;
import com.pk.petrolstationmonolith.models.loyaltyprogram.RequestChangeProgramParameters;
import com.pk.petrolstationmonolith.models.loyaltyprogram.RequestPointsChange;
import com.pk.petrolstationmonolith.models.loyaltyprogram.Points;
import com.pk.petrolstationmonolith.models.loyaltyprogram.ResponseProgramParameters;
import com.pk.petrolstationmonolith.repositories.account.UserRepository;
import com.pk.petrolstationmonolith.repositories.loyaltyprogram.LoyaltyProgramRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoyaltyProgramService {

    private final LoyaltyProgramRepository loyaltyProgramRepository;
    private final UserRepository userRepository;

    public LoyaltyProgramService(LoyaltyProgramRepository loyaltyProgramRepository, UserRepository userRepository) {
        this.loyaltyProgramRepository = loyaltyProgramRepository;
        this.userRepository = userRepository;
    }

    public Points joinLoyaltyProgram() {
        long userId = getUserIdFromAuth();
        if (loyaltyProgramRepository.findByUserId(userId).isPresent()) {
            throw new UserHasAlreadyJoinedTheLoyaltyProgramException(userId);
        }
        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        loyaltyProgram.setUser(getUserFromAuth());
        return new Points(loyaltyProgramRepository.save(loyaltyProgram).getPoints());
    }

    public Points getPoints() {
        return new Points(getLoyaltyProgram(getUserIdFromAuth()).getPoints());
    }

    public Points getPointsByUser(long userId) {
        return new Points(getLoyaltyProgram(userId).getPoints());
    }

    public Points addProgramPoints(Long userId, ServiceType serviceType, RequestPointsChange request) {
        LoyaltyProgram loyaltyProgram = getLoyaltyProgram(userId);

        long points = serviceType.getPoints() * request.getNumber();

        loyaltyProgram.setPoints(loyaltyProgram.getPoints() + points);

        return new Points(loyaltyProgramRepository.save(loyaltyProgram).getPoints());
    }

    public Points exchangePoints(ServiceType serviceType, RequestPointsChange request) {
        LoyaltyProgram loyaltyProgram = getLoyaltyProgram(getUserIdFromAuth());

        long points = serviceType.getCost() * request.getNumber();

        if (loyaltyProgram.getPoints() - points < 0) {
            throw new NotEnoughLoyaltyProgramPointsException(getUserIdFromAuth());
        }

        loyaltyProgram.setPoints(loyaltyProgram.getPoints() - points);

        return new Points(loyaltyProgramRepository.save(loyaltyProgram).getPoints());
    }

    public ResponseProgramParameters changeProgramParameters(ServiceType serviceType, RequestChangeProgramParameters request) {
        serviceType.setPoints(request.getPoints());
        serviceType.setCost(request.getCost());
        return new ResponseProgramParameters();
    }

    public ResponseProgramParameters getProgramParameters() {
        return new ResponseProgramParameters();
    }

    private LoyaltyProgram getLoyaltyProgram(Long userId) {
        return loyaltyProgramRepository.findByUserId(userId)
                .orElseThrow(() -> new UserHasNotJoinedTheLoyaltyProgramException(userId));
    }

    private User getUserFromAuth() {
        long id = getUserIdFromAuth();
        return userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User with id: " + id + " not found.")
        );
    }

    private long getUserIdFromAuth() {
        return Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
