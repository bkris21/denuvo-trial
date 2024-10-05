package bkris.project_customer_application.dtos.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDate creationDate;
}
