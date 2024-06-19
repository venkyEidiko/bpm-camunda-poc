package com.bpm_camunda_service.pack.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StartProcessResponse {

    private List<Link> links;
    String id;
    String definitionId;
    String businessKey;
    String caseInstanceId;
    String ended;
    String suspended;
    String tenantId;

}
