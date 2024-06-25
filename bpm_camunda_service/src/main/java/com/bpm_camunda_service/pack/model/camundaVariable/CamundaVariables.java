package com.bpm_camunda_service.pack.model.camundaVariable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CamundaVariables {

    private Map<String,Object> variables;
    private String businessKey;
}
