package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.IndividualDto;
import com.pk.petrolstationmonolith.entities.account.Individual;
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

    public void addIndividual(Individual individual) {
        log.trace("Adding new individual for user with id " + individual.getUser().getId());
        individualRepository.save(individual);
    }

    public IndividualDto updateIndividual(long userId, IndividualDto individualDto) {
        log.trace("Updating individual for user with id " + userId);
        Individual oldIndividual = getIndividual(userId);

        Individual individual = mapDtoToIndividual(individualDto);
        individual.setId(oldIndividual.getId());
        individual.setUser(oldIndividual.getUser());
        individual = individualRepository.save(individual);

        return mapIndividualToDto(individual);
    }

    public IndividualDto deleteIndividual(long userId) {
        log.trace("Deleting individual for user with id " + userId);
        Individual individual = getIndividual(userId);
        individualRepository.delete(individual);
        return mapIndividualToDto(individual);
    }

    private Individual getIndividual(long userId) {
        return individualRepository.findByUserId(userId)
                .orElseThrow(() -> new IndividualNotFoundException(userId));
    }

    private IndividualDto mapIndividualToDto(Individual individual) {
        return mapper.map(individual, IndividualDto.class);
    }

    private Individual mapDtoToIndividual(IndividualDto individualDto) {
        return mapper.map(individualDto, Individual.class);
    }

}
