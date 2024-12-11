package com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.clients.impl;

import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.advices.APIResponse;
import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.clients.EmployeeClient;
import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.dto.EmployeeDTO;
import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        try {
            APIResponse<List<EmployeeDTO>> employeeDTOList = restClient
                    .get()
                    .uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});
            return employeeDTOList.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        try {
            APIResponse<EmployeeDTO> employeeResponse = restClient
                    .get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});
            return employeeResponse.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try {
            APIResponse<EmployeeDTO> employeeResponse = restClient
                    .post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                        System.out.println("Error occurred: " + new String(response.getBody().readAllBytes()));
                        throw  new ResourceNotFoundException("Could not create employees");
                    }))
                    .body(new ParameterizedTypeReference<>() {});
            return employeeResponse.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
