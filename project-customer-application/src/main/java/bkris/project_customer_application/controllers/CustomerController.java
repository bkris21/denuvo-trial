package bkris.project_customer_application.controllers;

import bkris.project_customer_application.dtos.customer.CustomerRequest;
import bkris.project_customer_application.dtos.customer.CustomerResponse;
import bkris.project_customer_application.dtos.project.ProjectRequest;
import bkris.project_customer_application.dtos.project.ProjectResponse;
import bkris.project_customer_application.services.CustomerProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerProjectService customerProjectService;

    @PostMapping("/customer/project")
    public ResponseEntity<CustomerResponse> createCustomerWithProject(@RequestBody CustomerRequest customerRequest){
       CustomerResponse customerResponse = customerProjectService.createCustomerWithProject(customerRequest);
       return  ResponseEntity.ok(customerResponse);
    }

    @GetMapping("/customers/projects")
    public ResponseEntity<List<CustomerResponse>> getAllCustomersWithProjects(){
        List<CustomerResponse> customerResponses = customerProjectService.getAllCustomersWithProjects();
        return ResponseEntity.ok(customerResponses);
    }

    @GetMapping("/customer/project/{projectId}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable("projectId") Long projectId){
        ProjectResponse projectResponse = customerProjectService.getProjectById(projectId);
        return ResponseEntity.ok(projectResponse);
    }

    @PutMapping("/customer/project/{projectId}")
    public ResponseEntity<ProjectResponse> updateProjectById(@PathVariable("projectId") Long projectId, @RequestBody ProjectRequest projectRequest){
        ProjectResponse projectResponse = customerProjectService.updateProjectById(projectId, projectRequest);
        return ResponseEntity.ok(projectResponse);
    }


}
