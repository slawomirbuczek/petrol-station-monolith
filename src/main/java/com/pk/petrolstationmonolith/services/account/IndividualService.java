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

    public IndividualService(IndividualRepository individualRepository) {
        this.individualRepository = individualRepository;
    }

    public Individual addIndividual(Individual individual) {
        return individualRepository.save(individual);
    }

    public Individual getIndividualByUserId(long userId) {
        return individualRepository.findByUserId(userId).orElseThrow(() -> new IndividualNotFoundException(userId));
    }

    public IndividualDto mapIndividualToDto(Individual individual) {
        return new ModelMapper().map(individual, IndividualDto.class);
    }

}
