package com.pk.petrolstationmonolith.models.validation;

import com.pk.petrolstationmonolith.models.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationExceptionMessages {

    private List<ResponseMessage> exceptionsMessage;

}
