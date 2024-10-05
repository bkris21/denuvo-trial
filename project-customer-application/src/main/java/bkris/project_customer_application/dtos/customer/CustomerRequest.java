package bkris.project_customer_application.dtos.customer;

import bkris.project_customer_application.dtos.project.ProjectRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class CustomerRequest {
    private String name;
    private String contact;
    private Optional<ProjectRequest> project;
}
