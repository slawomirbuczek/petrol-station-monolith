package com.pk.petrolstationmonolith.services.raports;

import com.pk.petrolstationmonolith.dtos.monitoring.ParameterDto;
import com.pk.petrolstationmonolith.entities.monitoring.*;
import com.pk.petrolstationmonolith.models.monitoring.ResponseParameters;
import com.pk.petrolstationmonolith.properties.monitoring.MonitoringProperties;
import com.pk.petrolstationmonolith.repositories.monitoring.E95Repository;
import com.pk.petrolstationmonolith.repositories.monitoring.E98Repository;
import com.pk.petrolstationmonolith.repositories.monitoring.LpgRepository;
import com.pk.petrolstationmonolith.repositories.monitoring.OnRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final E95Repository e95Repository;
    private final E98Repository e98Repository;
    private final OnRepository onRepository;
    private final LpgRepository lpgRepository;
    private final ModelMapper modelMapper;

    public ReportService(E95Repository e95Repository, E98Repository e98Repository,
                         OnRepository onRepository, LpgRepository lpgRepository,
                         MonitoringProperties properties) {
        this.e95Repository = e95Repository;
        this.e98Repository = e98Repository;
        this.onRepository = onRepository;
        this.lpgRepository = lpgRepository;
        this.modelMapper = new ModelMapper();
    }


    public ResponseParameters getMonitoringMonthlyReport(int year, int month) {
        Date from = Date.valueOf(LocalDate.of(year, month, 1));
        Date to = Date.valueOf(LocalDate.of(year, month, YearMonth.of(year, month).lengthOfMonth()));
        List<E95> e95List = e95Repository.findAllByDateBetween(from, to);
        List<E98> e98List = e98Repository.findAllByDateBetween(from, to);
        List<On> onList = onRepository.findAllByDateBetween(from, to);
        List<Lpg> lpgList = lpgRepository.findAllByDateBetween(from, to);

        return new ResponseParameters(
                e95List.stream().map(this::mapParameterToDto).collect(Collectors.toList()),
                e98List.stream().map(this::mapParameterToDto).collect(Collectors.toList()),
                onList.stream().map(this::mapParameterToDto).collect(Collectors.toList()),
                lpgList.stream().map(this::mapParameterToDto).collect(Collectors.toList())
        );
    }

    private ParameterDto mapParameterToDto(Parameter parameter) {
        return Objects.isNull(parameter) ? null : modelMapper.map(parameter, ParameterDto.class);
    }

}

