package bkris.project_customer_application.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "contact")
    private String contact;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<ProjectEntity> projects = new HashSet<>();

    public CustomerEntity(String name, String contact) {
        this.name = name;
        this.contact = contact;
        this.creationDate = LocalDate.now();
    }


    public void addProject(ProjectEntity project){
        projects.add(project);
        project.setCustomer(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEntity that = (CustomerEntity) o;
        return Objects.equals(contact, that.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(contact);
    }
}
