package com.pk.petrolstationmonolith.services.supplies;

import com.pk.petrolstationmonolith.dtos.supplies.SupplyDto;
import com.pk.petrolstationmonolith.entities.supplies.Supply;
import com.pk.petrolstationmonolith.exceptions.supplies.SupplyNotFoundException;
import com.pk.petrolstationmonolith.models.supplies.Supplies;
import com.pk.petrolstationmonolith.repositories.supplies.SupplyRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SupplyService {

    private final SupplyRepository supplyRepository;
    private final ModelMapper mapper;

    public Supply getSupplyById(long id) {
        return supplyRepository.findById(id).orElseThrow(() -> new SupplyNotFoundException(id));
    }

    public Supplies getSuppliesByDateBetween(Optional<LocalDate> optionalFrom, Optional<LocalDate> optionalTo) {
        LocalDate from = optionalFrom.orElseGet(LocalDate::now);

        if (optionalTo.isEmpty()) {
            return new Supplies(supplyRepository.findAllByDateAfter(from));
        } else {
            return new Supplies(supplyRepository.findAllByDateBetween(from, optionalTo.get()));
        }

    }

    public Supply addSupply(SupplyDto supplyDto) {
        return supplyRepository.save(mapDtoToSupply(supplyDto));
    }

    public Supply updateSupply(long id, SupplyDto supplyDto) {
        if (supplyRepository.existsById(id)) {
            Supply supply = mapDtoToSupply(supplyDto);
            supply.setId(id);
            return supplyRepository.save(supply);
        } else {
            throw new SupplyNotFoundException(id);
        }
    }

    public Supply deleteSupply(long id) {
        Supply supply = getSupplyById(id);
        supplyRepository.deleteById(id);
        return supply;
    }

    private Supply mapDtoToSupply(SupplyDto supplyDto) {
        return mapper.map(supplyDto, Supply.class);
    }

}
