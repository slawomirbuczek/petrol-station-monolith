package com.pk.petrolstationmonolith.services.monitoring;

import com.pk.petrolstationmonolith.entities.monitoring.Parameter;
import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.monitoring.Parameters;
import com.pk.petrolstationmonolith.models.monitoring.RequestChangeInterval;
import com.pk.petrolstationmonolith.models.monitoring.RequestParametersBetweenDates;
import com.pk.petrolstationmonolith.properties.monitoring.MonitoringProperties;
import com.pk.petrolstationmonolith.repositories.monitoring.ParameterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MonitoringService {

    private final ParameterRepository parameterRepository;
    private final MonitoringProperties monitoringProperties;
    private final ModelMapper modelMapper;

    public void addParameter(Parameter parameter) {
        log.trace("Adding monitoring parameters");
        parameterRepository.save(parameter);
    }

    public Parameter getCurrentParameters() {
        log.trace("Getting current parameters");
        return parameterRepository.findTopByOrderByDateTimeDesc();
    }

    public Parameters getParametersBetweenDates(RequestParametersBetweenDates request) {
        log.trace("Getting parameters from " + request.getFrom() + " to " + request.getTo());
        List<Parameter> parameters = parameterRepository
                .findAllByDateTimeBetween(request.getFrom().atStartOfDay(), request.getTo().plusDays(1).atStartOfDay());
        return new Parameters(parameters);
    }

    public Parameters getMonitoringMonthlyReport(Optional<Integer> optionalYear, Optional<Integer> optionalMonth) {
        int year = optionalYear.orElseGet(() -> LocalDate.now().getYear());
        int month = optionalMonth.orElseGet(() -> LocalDate.now().getMonthValue());
        log.trace("Getting monitoring monthly report for year " + year + " and month " + month);
        LocalDate dateFrom = LocalDate.of(year, month, 1);
        LocalDate dateTo = LocalDate.of(year, month, YearMonth.of(year, month).lengthOfMonth());

        List<Parameter> parameters = parameterRepository
                .findAllByDateTimeBetween(dateFrom.atStartOfDay(), dateTo.plusDays(1).atStartOfDay());

        return new Parameters(parameters);
    }

    public ResponseMessage changeMonitoringInterval(RequestChangeInterval request) {
        log.trace("Changing monitoring interval to " + request.getInterval() + " minutes");
        monitoringProperties.setInterval(request.getInterval());
        return new ResponseMessage("Interval has been set to " + request.getInterval() + " minutes.");
    }

}
