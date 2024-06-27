package com.bpm_camunda_service.pack.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnAssignRequest {

    boolean unassigned;
    String taskDefinitionKey;
}
