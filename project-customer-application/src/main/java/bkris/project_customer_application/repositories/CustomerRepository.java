package bkris.project_customer_application.repositories;

import bkris.project_customer_application.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query("select c from CustomerEntity c join fetch c.projects where c.name =:customerName and c.contact = :contact")
    CustomerEntity findByNameAndContact(@Param("customerName") String name, @Param("contact") String contact);

    @Query("select c from CustomerEntity c join fetch c.projects")
    List<CustomerEntity> findAllCustomers();

    @Query("select c from CustomerEntity c join fetch c.projects p where p.creationDate between :from and :to")
    List<CustomerEntity> findAllCustomersWhereProjectsBetween(@Param("from") LocalDate from, @Param("to") LocalDate to);
}
