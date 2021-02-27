package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.IndividualDto;
import com.pk.petrolstationmonolith.entities.account.Individual;
import com.pk.petrolstationmonolith.exceptions.account.IndividualNotFoundException;
import com.pk.petrolstationmonolith.repositories.account.IndividualRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class IndividualService {

    private final IndividualRepository individualRepository;
    private final ModelMapper mapper;

    public IndividualService(IndividualRepository individualRepository, ModelMapper mapper) {
        this.individualRepository = individualRepository;
        this.mapper = mapper;
    }

    public IndividualDto getIndividualDto(long userId) {
        return mapIndividualToDto(getIndividual(userId));
    }

    public void addIndividual(Individual individual) {
        individualRepository.save(individual);
    }

    public IndividualDto updateIndividual(long userId, IndividualDto individualDto) {
        Individual oldIndividual = getIndividual(userId);

        Individual individual = mapDtoToIndividual(individualDto);
        individual.setId(oldIndividual.getId());
        individual.setUser(oldIndividual.getUser());
        individual = individualRepository.save(individual);

        return mapIndividualToDto(individual);
    }

    public IndividualDto deleteIndividual(long userId) {
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
