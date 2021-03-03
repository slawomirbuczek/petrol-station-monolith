package com.pk.petrolstationmonolith.services.supplies;

import com.pk.petrolstationmonolith.dtos.supplies.SupplyDto;
import com.pk.petrolstationmonolith.entities.supplies.Supply;
import com.pk.petrolstationmonolith.exceptions.supplies.SupplyNotFoundException;
import com.pk.petrolstationmonolith.models.supplies.Supplies;
import com.pk.petrolstationmonolith.repositories.supplies.SupplyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class SupplyService {

    private final SupplyRepository supplyRepository;
    private final ModelMapper mapper;

    public Supply getSupplyById(long id) {
        log.trace("Getting supply with id " + id);
        return supplyRepository.findById(id).orElseThrow(() -> new SupplyNotFoundException(id));
    }

    public Supplies getSuppliesByDateBetween(Optional<LocalDate> optionalFrom, Optional<LocalDate> optionalTo) {
        LocalDate from = optionalFrom.orElseGet(LocalDate::now);

        if (optionalTo.isEmpty()) {
            log.trace("Getting supplies by date from " + from);
            return new Supplies(supplyRepository.findAllByDateAfter(from));
        } else {
            log.trace("Getting supplies by date from " + from + " to " + optionalTo.get());
            return new Supplies(supplyRepository.findAllByDateBetween(from, optionalTo.get()));
        }

    }

    public Supply addSupply(SupplyDto supplyDto) {
        log.trace("Adding new supply " + supplyDto.toString());
        return supplyRepository.save(mapDtoToSupply(supplyDto));
    }

    public Supply updateSupply(long id, SupplyDto supplyDto) {
        if (supplyRepository.existsById(id)) {
            Supply supply = mapDtoToSupply(supplyDto);
            supply.setId(id);
            log.trace("Updating supply " + supply.toString());
            return supplyRepository.save(supply);
        } else {
            log.error("Supply with id " + id + " not found");
            throw new SupplyNotFoundException(id);
        }
    }

    public Supply deleteSupply(long id) {
        Supply supply = getSupplyById(id);
        log.trace("Deleting supply " + supply.toString());
        supplyRepository.deleteById(id);
        return supply;
    }

    private Supply mapDtoToSupply(SupplyDto supplyDto) {
        return mapper.map(supplyDto, Supply.class);
    }

}
