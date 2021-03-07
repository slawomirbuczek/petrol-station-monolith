package com.pk.petrolstationmonolith.services.loyaltyprogram;

import com.pk.petrolstationmonolith.entities.loyaltyprogram.LoyaltyProgram;
import com.pk.petrolstationmonolith.enums.ServiceType;
import com.pk.petrolstationmonolith.exceptions.loyaltyprogram.NotEnoughLoyaltyProgramPointsException;
import com.pk.petrolstationmonolith.exceptions.loyaltyprogram.CustomerHasAlreadyJoinedTheLoyaltyProgramException;
import com.pk.petrolstationmonolith.exceptions.loyaltyprogram.CustomerHasNotJoinedTheLoyaltyProgramException;
import com.pk.petrolstationmonolith.models.loyaltyprogram.Points;
import com.pk.petrolstationmonolith.models.loyaltyprogram.ProgramParameters;
import com.pk.petrolstationmonolith.models.loyaltyprogram.RequestChangeProgramParameters;
import com.pk.petrolstationmonolith.repositories.loyaltyprogram.LoyaltyProgramRepository;
import com.pk.petrolstationmonolith.services.account.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class LoyaltyProgramService {

    private final LoyaltyProgramRepository loyaltyProgramRepository;
    private final CustomerService customerService;

    public Points joinLoyaltyProgram(long customerId) {
        if (loyaltyProgramRepository.findByCustomersId(customerId).isPresent()) {
            throw new CustomerHasAlreadyJoinedTheLoyaltyProgramException(customerId);
        }
        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        loyaltyProgram.setCustomers(customerService.getCustomer(customerId));
        log.trace("Customer with id " + customerId + " joined loyalty program");
        return new Points(loyaltyProgramRepository.save(loyaltyProgram).getPoints());
    }

    public Points getPoints(long customerId) {
        log.trace("Getting points for customer with id " + customerId);
        return new Points(getLoyaltyProgram(customerId).getPoints());
    }

    public Points addProgramPoints(long customerId, ServiceType serviceType, Points points) {
        LoyaltyProgram loyaltyProgram = getLoyaltyProgram(customerId);

        long additionalPoints = serviceType.getPoints() * points.getPoints();
        log.trace("Adding " + additionalPoints + " points to customer with id " + customerId);
        loyaltyProgram.setPoints(loyaltyProgram.getPoints() + additionalPoints);

        return new Points(loyaltyProgramRepository.save(loyaltyProgram).getPoints());
    }

    public void addProgramPointsAfterTransaction(long customerId, ServiceType serviceType, int number) {
        Optional<LoyaltyProgram> optionalLoyaltyProgram = loyaltyProgramRepository.findByCustomersId(customerId);

        if (optionalLoyaltyProgram.isEmpty()) {
            return;
        }

        LoyaltyProgram loyaltyProgram = optionalLoyaltyProgram.get();

        long points = serviceType.getPoints() * number;
        log.trace("Adding " + points + " points to customer with id " + customerId + " after transaction");
        loyaltyProgram.setPoints(loyaltyProgram.getPoints() + points);

        loyaltyProgramRepository.save(loyaltyProgram);
    }

    public Points exchangePoints(long customerId, ServiceType serviceType, Points points) {
        LoyaltyProgram loyaltyProgram = getLoyaltyProgram(customerId);

        long exchangingPoints = serviceType.getCost() * points.getPoints();

        if (loyaltyProgram.getPoints() - exchangingPoints < 0) {
            throw new NotEnoughLoyaltyProgramPointsException(customerId);
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

    private LoyaltyProgram getLoyaltyProgram(Long customerId) {
        return loyaltyProgramRepository.findByCustomersId(customerId)
                .orElseThrow(() -> new CustomerHasNotJoinedTheLoyaltyProgramException(customerId));
    }

}
