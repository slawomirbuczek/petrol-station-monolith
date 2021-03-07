package com.pk.petrolstationmonolith.services.monitoring;

import com.pk.petrolstationmonolith.dtos.monitoring.ParametersDto;
import com.pk.petrolstationmonolith.entities.monitoring.Parameters;
import com.pk.petrolstationmonolith.enums.FuelType;
import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.monitoring.ListOfParameters;
import com.pk.petrolstationmonolith.models.monitoring.RequestChangeInterval;
import com.pk.petrolstationmonolith.models.monitoring.RequestParametersBetweenDates;
import com.pk.petrolstationmonolith.properties.monitoring.MonitoringProperties;
import com.pk.petrolstationmonolith.repositories.monitoring.ParametersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class MonitoringService {

    private final ParametersRepository parametersRepository;
    private final MonitoringProperties monitoringProperties;
    private final ModelMapper mapper;

    public void addParameter(ParametersDto parametersDto) {
        log.trace("Adding monitoring parameters");
        parametersRepository.save(mapDtoToParameters(parametersDto));
    }

    public ListOfParameters getCurrentParameters() {
        log.trace("Getting current parameters");
        List<ParametersDto> parameters = Arrays.stream(FuelType.values()).map(
                parametersRepository::findTopByFuelTypeOrderByDateTimeDesc
        ).map(this::mapParametersToDto).collect(Collectors.toList());
        return new ListOfParameters(parameters);
    }

    public ListOfParameters getParametersBetweenDates(RequestParametersBetweenDates request) {
        LocalDateTime dateTimeFrom = request.getFrom().atStartOfDay();
        LocalDateTime dateTimeTo = request.getTo().plusDays(1).atStartOfDay();
        log.trace("Getting parameters from " + dateTimeFrom + " to " + dateTimeTo);

        List<Parameters> parameters = parametersRepository
                .findAllByDateTimeBetweenAndFuelType(dateTimeFrom, dateTimeTo, request.getFuelType());

        return new ListOfParameters(mapParametersListToDto(parameters));
    }

    public ListOfParameters getMonitoringMonthlyReport(Optional<Integer> optionalYear, Optional<Integer> optionalMonth) {
        int year = optionalYear.orElseGet(() -> LocalDate.now().getYear());
        int month = optionalMonth.orElseGet(() -> LocalDate.now().getMonthValue());
        log.trace("Getting monitoring monthly report for year " + year + " and month " + month);
        LocalDateTime dateTimeFrom = LocalDate.of(year, month, 1).atStartOfDay();
        LocalDateTime dateTimeTo = LocalDate.of(year, month, YearMonth.of(year, month).lengthOfMonth()).plusDays(1).atStartOfDay();

        List<ParametersDto> parameters = Arrays.stream(FuelType.values()).map(
                fuelType -> parametersRepository.findAllByDateTimeBetweenAndFuelType(dateTimeFrom, dateTimeTo, fuelType)
        ).flatMap(Collection::stream).map(this::mapParametersToDto).collect(Collectors.toList());

        return new ListOfParameters(parameters);
    }

    public ResponseMessage changeMonitoringInterval(RequestChangeInterval request) {
        log.trace("Changing monitoring interval to " + request.getInterval() + " minutes");
        monitoringProperties.setInterval(request.getInterval());
        return new ResponseMessage("Interval has been set to " + request.getInterval() + " minutes.");
    }

    private ParametersDto mapParametersToDto(Parameters parameters) {
        return mapper.map(parameters, ParametersDto.class);
    }

    private Parameters mapDtoToParameters(ParametersDto parametersDto) {
        return mapper.map(parametersDto, Parameters.class);
    }

    private List<ParametersDto> mapParametersListToDto(List<Parameters> parameters) {
        return parameters.stream().map(this::mapParametersToDto).collect(Collectors.toList());
    }

}
