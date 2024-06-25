package com.bpm_camunda_service.pack.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskCamundaResponse {
    String id;
    String processDefinitionKey;
    String processDefinitionId;
    String processInstanceId;
    String executionId;
    String caseDefinitionKey;
    String caseDefinitionId;
    String caseInstanceId;
    String caseExecutionId;
    String activityInstanceId;
    String name;
    String description;
    String deleteReason;
    String owner;
    String assignee;
    Date startTime;
    Date endTime;
    String duration;
    String taskDefinitionKey;
    int priority;
    String due;
    String parentTaskId;
    String followUp;
    String tenantId;
    String removalTime;
    String rootProcessInstanceId;
}
