package com.bpm_camunda_service.pack.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDetails {

    String processInstanceId;
    String id;
    Date startTime;
    String Stage;
    String processDefinitionKey;

}
