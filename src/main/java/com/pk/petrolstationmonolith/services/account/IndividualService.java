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

    public IndividualDto getIndividualDto(long userId) {
        log.trace("Getting individual dto for user with id " + userId);
        return mapIndividualToDto(getIndividual(userId));
    }

    public void addIndividual(Individuals individuals) {
        log.trace("Adding new individual for user with id " + individuals.getCustomers().getId());
        individualRepository.save(individuals);
    }

    public IndividualDto updateIndividual(long userId, IndividualDto individualDto) {
        log.trace("Updating individual for user with id " + userId);
        Individuals oldIndividuals = getIndividual(userId);

        Individuals individuals = mapDtoToIndividual(individualDto);
        individuals.setId(oldIndividuals.getId());
        individuals.setCustomers(oldIndividuals.getCustomers());
        individuals = individualRepository.save(individuals);

        return mapIndividualToDto(individuals);
    }

    public IndividualDto deleteIndividual(long userId) {
        log.trace("Deleting individual for user with id " + userId);
        Individuals individuals = getIndividual(userId);
        individualRepository.delete(individuals);
        return mapIndividualToDto(individuals);
    }

    private Individuals getIndividual(long userId) {
        return individualRepository.findByUserId(userId)
                .orElseThrow(() -> new IndividualNotFoundException(userId));
    }

    private IndividualDto mapIndividualToDto(Individuals individuals) {
        return mapper.map(individuals, IndividualDto.class);
    }

    private Individuals mapDtoToIndividual(IndividualDto individualDto) {
        return mapper.map(individualDto, Individuals.class);
    }

}
