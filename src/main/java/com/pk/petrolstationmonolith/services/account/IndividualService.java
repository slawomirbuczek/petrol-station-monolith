package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.IndividualDto;
import com.pk.petrolstationmonolith.entities.account.Individuals;
import com.pk.petrolstationmonolith.exceptions.account.IndividualNotFoundException;
import com.pk.petrolstationmonolith.repositories.account.IndividualRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class IndividualService {

    private final IndividualRepository individualRepository;
    private final ModelMapper mapper;

    public IndividualDto getIndividualDto(long customerId) {
        log.trace("Getting individual dto for customer with id " + customerId);
        return mapIndividualToDto(getIndividual(customerId));
    }

    public void addIndividual(Individuals individuals) {
        log.trace("Adding new individual for customer with id " + individuals.getCustomers().getId());
        individualRepository.save(individuals);
    }

    public IndividualDto updateIndividual(long customerId, IndividualDto individualDto) {
        log.trace("Updating individual for customer with id " + customerId);
        Individuals oldIndividuals = getIndividual(customerId);

        Individuals individuals = mapDtoToIndividual(individualDto);
        individuals.setId(oldIndividuals.getId());
        individuals.setCustomers(oldIndividuals.getCustomers());
        individuals = individualRepository.save(individuals);

        return mapIndividualToDto(individuals);
    }

    public IndividualDto deleteIndividual(long customerId) {
        log.trace("Deleting individual for customer with id " + customerId);
        Individuals individuals = getIndividual(customerId);
        individualRepository.delete(individuals);
        return mapIndividualToDto(individuals);
    }

    private Individuals getIndividual(long customerId) {
        return individualRepository.findByCustomersId(customerId)
                .orElseThrow(() -> new IndividualNotFoundException(customerId));
    }

    private IndividualDto mapIndividualToDto(Individuals individuals) {
        return mapper.map(individuals, IndividualDto.class);
    }

    private Individuals mapDtoToIndividual(IndividualDto individualDto) {
        return mapper.map(individualDto, Individuals.class);
    }

}
