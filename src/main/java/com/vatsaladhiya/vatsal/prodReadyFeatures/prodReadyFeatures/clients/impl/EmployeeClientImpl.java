package com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.clients.impl;

import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.advices.APIResponse;
import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.clients.EmployeeClient;
import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.dto.EmployeeDTO;
import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        logger.trace("Trying to retrieve all employees");
        try {
            APIResponse<List<EmployeeDTO>> employeeDTOList = restClient
                    .get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        logger.error(new String(response.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not find employees");
                    })
                    .body(new ParameterizedTypeReference<>() {});
            logger.info("Successfully retrieved the employees in getAllEmployees");
            return employeeDTOList.getData();
        } catch (Exception e) {
            logger.error("Exception occurred in getAllEmployees", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        logger.trace("Trying to retrieve employee by id with id: {}", employeeId);
        try {
            APIResponse<EmployeeDTO> employeeResponse = restClient
                    .get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        logger.error(new String(response.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not find employee with id: " + employeeId);
                    })
                    .body(new ParameterizedTypeReference<>() {});
            return employeeResponse.getData();
        } catch (Exception e) {
            logger.error("Exception occurred in getEmployeeById", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        logger.trace("Trying to create new employee with information: {}", employeeDTO);
        try {
            APIResponse<EmployeeDTO> employeeResponse = restClient
                    .post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                        logger.debug("4xx error occurred while creating new employee");
                        logger.error("Error occurred: {}", new String(response.getBody().readAllBytes()));
                        throw  new ResourceNotFoundException("Could not create employees");
                    }))
                    .body(new ParameterizedTypeReference<>() {});
            logger.trace("Successfully created new employee with data: {}", employeeResponse.getData());
            return employeeResponse.getData();
        } catch (Exception e) {
            logger.error("Error occurred while creating new employee");
            throw new RuntimeException(e);
        }
    }
}
