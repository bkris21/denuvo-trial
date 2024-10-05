package bkris.project_customer_application.controllers;

import bkris.project_customer_application.dtos.customer.CustomerRequest;
import bkris.project_customer_application.dtos.customer.CustomerResponse;
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


}
