package bkris.project_customer_application.controllers;

import bkris.project_customer_application.dtos.customer.CustomerRequest;
import bkris.project_customer_application.dtos.customer.CustomerResponse;
import bkris.project_customer_application.dtos.project.ProjectRequest;
import bkris.project_customer_application.dtos.project.ProjectResponse;
import bkris.project_customer_application.services.CustomerProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping(value = "/customers/projects/zip", produces = "application/zip")
    public ResponseEntity<ByteArrayResource> getAllProjectsBetween(@RequestParam("startDate") LocalDate from, @RequestParam("endDate") LocalDate to){
        ByteArrayResource resource = customerProjectService.getAllProjectsBetween(from, to);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\"files.zip\"" )
                .contentLength(resource.contentLength())
                .contentType(MediaType.valueOf("application/zip"))
                .body(resource);
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

    @DeleteMapping("/customer/project/{projectId}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable("projectId") Long projectId){
        customerProjectService.deleteProjectById(projectId);
        return ResponseEntity.noContent().build();
    }


}
