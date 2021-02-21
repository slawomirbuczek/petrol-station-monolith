package com.pk.petrolstationmonolith.services.monitoring;

import com.pk.petrolstationmonolith.dtos.monitoring.ParameterDto;
import com.pk.petrolstationmonolith.entities.monitoring.Parameter;
import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.monitoring.RequestChangeInterval;
import com.pk.petrolstationmonolith.models.monitoring.RequestParametersBetweenDates;
import com.pk.petrolstationmonolith.models.monitoring.ResponseParameters;
import com.pk.petrolstationmonolith.properties.monitoring.MonitoringProperties;
import com.pk.petrolstationmonolith.repositories.monitoring.ParameterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MonitoringService {

    private final ParameterRepository parameterRepository;
    private final MonitoringProperties monitoringProperties;
    private final ModelMapper modelMapper;

    public MonitoringService(ParameterRepository parameterRepository, MonitoringProperties monitoringProperties) {
        this.parameterRepository = parameterRepository;
        this.monitoringProperties = monitoringProperties;
        this.modelMapper = new ModelMapper();
    }

    public ParameterDto getCurrentParameters() {
        return modelMapper.map(parameterRepository.findTopByOrderByIdDesc(), ParameterDto.class);
    }

    public ResponseParameters getParametersBetweenDates(RequestParametersBetweenDates request) {
        List<Parameter> parameters = parameterRepository
                .findAllByDateTimeBetween(request.getFrom().atStartOfDay(), request.getTo().plusDays(1).atStartOfDay());
        return new ResponseParameters(mapParameterListToDto(parameters));
    }

    public ResponseParameters getMonitoringMonthlyReport(int year, int month) {
        LocalDate dateFrom = LocalDate.of(year, month, 1);
        LocalDate dateTo = LocalDate.of(year, month, YearMonth.of(year, month).lengthOfMonth());

        List<Parameter> parameters = parameterRepository
                .findAllByDateTimeBetween(dateFrom.atStartOfDay(), dateTo.plusDays(1).atStartOfDay());

        return new ResponseParameters(mapParameterListToDto(parameters));
    }

    public ResponseMessage changeMonitoringInterval(RequestChangeInterval request) {
        monitoringProperties.setInterval(request.getInterval());
        return new ResponseMessage("Interval has been set to " + request.getInterval() + " minutes.");
    }

    private ParameterDto mapParameterToDto(Parameter parameter) {
        return Objects.isNull(parameter) ? null : modelMapper.map(parameter, ParameterDto.class);
    }

    private List<ParameterDto> mapParameterListToDto(List<? extends Parameter> parameters) {
        return parameters.stream().map(this::mapParameterToDto).collect(Collectors.toList());
    }

}
