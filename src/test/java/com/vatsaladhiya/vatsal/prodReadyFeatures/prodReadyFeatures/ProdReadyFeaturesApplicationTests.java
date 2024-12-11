package com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures;

import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.clients.EmployeeClient;
import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.dto.EmployeeDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProdReadyFeaturesApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

	@Test
	@Order(1)
	void getAllEmployees() {
		List<EmployeeDTO> employeeDTOList = employeeClient.getAllEmployees();
		System.out.println(employeeDTOList);
	}

	@Test
	@Order(2)
	void createNewEmployee() {
		EmployeeDTO employeeDTO = new EmployeeDTO(
				null,
				"Vatsal",
				"vatsal@test.com",
				2,
				LocalDate.of(2023, 1,1),
				true
		);
		EmployeeDTO employeeDTO1 = employeeClient.createNewEmployee(employeeDTO);
		System.out.println(employeeDTO1);
	}

	@Test
	@Order(3)
	void getEmployeeById() {
		EmployeeDTO employeeDTO = employeeClient.getEmployeeById(52L);
		System.out.println(employeeDTO);
	}

}
