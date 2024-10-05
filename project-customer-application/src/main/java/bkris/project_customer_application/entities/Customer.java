package bkris.project_customer_application.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "contact")
    private String contact;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @OneToMany(mappedBy = "customer")
    private Set<Project> projects = new HashSet<>();

    public Customer(String name, String contact, LocalDate creationDate) {
        this.name = name;
        this.contact = contact;
        this.creationDate = creationDate;
    }
}
