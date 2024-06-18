package com.bpm_camunda_service.pack.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ProblemDetail;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commonresponse {

    List<Object> result;
    ProblemDetail problem;
}
