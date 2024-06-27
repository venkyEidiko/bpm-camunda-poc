package com.bpm_camunda_service.pack.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompleteTaskRequest {
    String taskId;
    int empId;
}
