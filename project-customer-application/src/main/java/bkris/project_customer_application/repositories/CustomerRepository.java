package bkris.project_customer_application.repositories;

import bkris.project_customer_application.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query("select c from CustomerEntity c join fetch c.projects where c.name =:customerName and c.contact = :contact")
    CustomerEntity findByNameAndContact(@Param("customerName") String name, @Param("contact") String contact);
}
