package bkris.project_customer_application.services;

import bkris.project_customer_application.dtos.customer.CustomerRequest;
import bkris.project_customer_application.dtos.customer.CustomerResponse;
import bkris.project_customer_application.dtos.project.ProjectRequest;
import bkris.project_customer_application.dtos.project.ProjectResponse;
import bkris.project_customer_application.entities.CustomerEntity;
import bkris.project_customer_application.entities.ProjectEntity;
import bkris.project_customer_application.exceptions.ProjectNotFoundException;
import bkris.project_customer_application.mappers.CustomerProjectMapper;
import bkris.project_customer_application.repositories.CustomerRepository;
import bkris.project_customer_application.repositories.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<CustomerResponse> getAllCustomersWithProjects() {
        List<CustomerEntity> customers = customerRepository.findAllCustomers();
        return mapper.mapToCustomerListResponse(customers);
    }

    public ProjectResponse getProjectById(Long projectId) {
        ProjectEntity projectEntity = findProject(projectId);
        return mapper.mapToProjectResponse(projectEntity);
    }

    @Modifying
    public ProjectResponse updateProjectById(Long projectId, ProjectRequest projectRequest) {
        ProjectEntity projectEntity = findProject(projectId);
        projectEntity.setName(projectRequest.getName());
        projectEntity.setDescription(projectRequest.getDescription());
        projectRepository.save(projectEntity);
        return mapper.mapToProjectResponse(projectEntity);
    }


    private ProjectEntity findProject(Long projectId){
        return projectRepository.findById(projectId).orElseThrow(()-> new ProjectNotFoundException("Project Not found with id: "+projectId));
    }
}
