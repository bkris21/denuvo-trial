package bkris.project_customer_application.services;

import bkris.project_customer_application.dtos.customer.CustomerRequest;
import bkris.project_customer_application.dtos.customer.CustomerResponse;
import bkris.project_customer_application.entities.CustomerEntity;
import bkris.project_customer_application.entities.ProjectEntity;
import bkris.project_customer_application.mappers.CustomerProjectMapper;
import bkris.project_customer_application.repositories.CustomerRepository;
import bkris.project_customer_application.repositories.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerProjectService {

    private final CustomerProjectMapper mapper;
    private final CustomerRepository customerRepository;
    private final ProjectRepository projectRepository;


    @Modifying
    @Transactional
    public CustomerResponse createCustomerWithProject(CustomerRequest customerRequest) {
        ProjectEntity projectEntity = customerRequest.getProject().map(project -> new ProjectEntity(project.getName(), project.getDescription())).orElse(null);
        CustomerEntity customerEntity = customerRepository.findByNameAndContact(customerRequest.getName(), customerRequest.getContact());

        if (customerEntity == null) {
            customerEntity = new CustomerEntity(customerRequest.getName(), customerRequest.getContact());
        }
        if(projectEntity != null) {
            customerEntity.addProject(projectEntity);
        }
        return mapper.mapToCustomerResponse(customerRepository.save(customerEntity));
    }
}
