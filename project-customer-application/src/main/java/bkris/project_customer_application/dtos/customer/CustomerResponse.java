package bkris.project_customer_application.dtos.customer;

import bkris.project_customer_application.dtos.project.ProjectResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerResponse {

    private Long id;
    private String name;
    private String contact;
    private LocalDate creationDate;
    private Set<ProjectResponse> projects;
}
