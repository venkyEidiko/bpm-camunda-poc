package com.bpm_camunda_service.pack.model.camundaVariable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CamundaChildVariables {

    private String type;
    private Object value;

}
