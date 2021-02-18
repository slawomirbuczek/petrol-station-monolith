package com.pk.petrolstationmonolith.services.monitoring;

import com.pk.petrolstationmonolith.dtos.monitoring.ParameterDto;
import com.pk.petrolstationmonolith.entities.monitoring.*;
import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.monitoring.CurrentParameters;
import com.pk.petrolstationmonolith.models.monitoring.Parameters;
import com.pk.petrolstationmonolith.models.monitoring.RequestChangeInterval;
import com.pk.petrolstationmonolith.models.monitoring.RequestParametersBetweenDates;
import com.pk.petrolstationmonolith.properties.monitoring.MonitoringProperties;
import com.pk.petrolstationmonolith.repositories.monitoring.E95Repository;
import com.pk.petrolstationmonolith.repositories.monitoring.E98Repository;
import com.pk.petrolstationmonolith.repositories.monitoring.LpgRepository;
import com.pk.petrolstationmonolith.repositories.monitoring.OnRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MonitoringService {

    private final E95Repository e95Repository;
    private final E98Repository e98Repository;
    private final OnRepository onRepository;
    private final LpgRepository lpgRepository;
    private final MonitoringProperties monitoringProperties;
    private final ModelMapper modelMapper;

    public MonitoringService(E95Repository e95Repository, E98Repository e98Repository,
                             OnRepository onRepository, LpgRepository lpgRepository,
                             MonitoringProperties monitoringProperties) {
        this.e95Repository = e95Repository;
        this.e98Repository = e98Repository;
        this.onRepository = onRepository;
        this.lpgRepository = lpgRepository;
        this.monitoringProperties = monitoringProperties;
        this.modelMapper = new ModelMapper();
    }

    public CurrentParameters getCurrentParameters() {
        E95 e95 = e95Repository.findTopByOrderByIdDesc();
        E98 e98 = e98Repository.findTopByOrderByIdDesc();
        On on = onRepository.findTopByOrderByIdDesc();
        Lpg lpg = lpgRepository.findTopByOrderByIdDesc();
        return new CurrentParameters(
                mapParameterToDto(e95),
                mapParameterToDto(e98),
                mapParameterToDto(on),
                mapParameterToDto(lpg)
        );
    }

    public Parameters getParametersBetweenDates(RequestParametersBetweenDates request) {
        List<E95> e95List = e95Repository.findAllByDateBetween(request.getFrom(), request.getTo());
        List<E98> e98List = e98Repository.findAllByDateBetween(request.getFrom(), request.getTo());
        List<On> onList = onRepository.findAllByDateBetween(request.getFrom(), request.getTo());
        List<Lpg> lpgList = lpgRepository.findAllByDateBetween(request.getFrom(), request.getTo());

        return new Parameters(
                e95List.stream().map(this::mapParameterToDto).collect(Collectors.toList()),
                e98List.stream().map(this::mapParameterToDto).collect(Collectors.toList()),
                onList.stream().map(this::mapParameterToDto).collect(Collectors.toList()),
                lpgList.stream().map(this::mapParameterToDto).collect(Collectors.toList())
        );
    }

    public ResponseMessage changeMonitoringInterval(RequestChangeInterval request) {
        monitoringProperties.setInterval(request.getInterval());
        return new ResponseMessage("Interval has been set to " + request.getInterval() + " minutes.");
    }

    private ParameterDto mapParameterToDto(Parameter parameter) {
        return Objects.isNull(parameter) ? null : modelMapper.map(parameter, ParameterDto.class);
    }

}
