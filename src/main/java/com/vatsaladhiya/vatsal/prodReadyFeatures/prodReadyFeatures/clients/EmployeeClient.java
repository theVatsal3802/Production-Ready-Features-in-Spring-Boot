package com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.clients;

import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(Long employeeId);

    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);
}
