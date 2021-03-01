package com.pk.petrolstationmonolith.services.monitoring;

import com.pk.petrolstationmonolith.entities.monitoring.Parameter;
import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.monitoring.Parameters;
import com.pk.petrolstationmonolith.models.monitoring.RequestChangeInterval;
import com.pk.petrolstationmonolith.models.monitoring.RequestParametersBetweenDates;
import com.pk.petrolstationmonolith.properties.monitoring.MonitoringProperties;
import com.pk.petrolstationmonolith.repositories.monitoring.ParameterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MonitoringService {

    private final ParameterRepository parameterRepository;
    private final MonitoringProperties monitoringProperties;
    private final ModelMapper modelMapper;

    public void addParameter(Parameter parameter) {
        parameterRepository.save(parameter);
    }

    public Parameter getCurrentParameters() {
        return parameterRepository.findTopByOrderByDateTimeDesc();
    }

    public Parameters getParametersBetweenDates(RequestParametersBetweenDates request) {
        List<Parameter> parameters = parameterRepository
                .findAllByDateTimeBetween(request.getFrom().atStartOfDay(), request.getTo().plusDays(1).atStartOfDay());
        return new Parameters(parameters);
    }

    public Parameters getMonitoringMonthlyReport(Optional<Integer> optionalYear, Optional<Integer> optionalMonth) {
        int year = optionalYear.orElseGet(() -> LocalDate.now().getYear());
        int month = optionalMonth.orElseGet(() -> LocalDate.now().getMonthValue());

        LocalDate dateFrom = LocalDate.of(year, month, 1);
        LocalDate dateTo = LocalDate.of(year, month, YearMonth.of(year, month).lengthOfMonth());

        List<Parameter> parameters = parameterRepository
                .findAllByDateTimeBetween(dateFrom.atStartOfDay(), dateTo.plusDays(1).atStartOfDay());

        return new Parameters(parameters);
    }

    public ResponseMessage changeMonitoringInterval(RequestChangeInterval request) {
        monitoringProperties.setInterval(request.getInterval());
        return new ResponseMessage("Interval has been set to " + request.getInterval() + " minutes.");
    }

}
