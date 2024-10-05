package bkris.project_customer_application.repositories;

import bkris.project_customer_application.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByNameAndContact(@Param("customer_name") String name, @Param("contact") String contact);
}
