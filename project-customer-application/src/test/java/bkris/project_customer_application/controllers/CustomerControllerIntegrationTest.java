package bkris.project_customer_application.controllers;

import bkris.project_customer_application.dtos.customer.CustomerRequest;
import bkris.project_customer_application.dtos.customer.CustomerResponse;
import bkris.project_customer_application.dtos.project.ProjectRequest;
import bkris.project_customer_application.dtos.project.ProjectResponse;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "spring.flyway.clean-disabled=false")
@Import(TestcontainersConfiguration.class)
public class CustomerControllerIntegrationTest {

    @Autowired
    Flyway flyway;

    @Autowired
    WebTestClient webTestClient;

    @BeforeEach
    void setUp(){
        flyway.clean();
        flyway.migrate();
    }

    @Test
    void testSaveCustomerWithoutProject(){
        webTestClient.post()
                .uri("/api/customer/project")
                .bodyValue(new CustomerRequest("Customer1", "c1@mail.com", Optional.empty()))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(CustomerResponse.class)
                .value( customerResponse -> {
                    assertThat(customerResponse.getId()).isNotNull();
                    assertThat(customerResponse.getName()).isEqualTo("Customer1");
                    assertThat(customerResponse.getCreationDate()).isEqualTo(LocalDate.now());
                    assertThat(customerResponse.getContact()).isEqualTo("c1@mail.com");
                    assertThat(customerResponse.getProjects()).isEmpty();
                });
    }


    @Test
    void testSaveCustomerWitProject(){
        webTestClient.post()
                .uri("/api/customer/project")
                .bodyValue(new CustomerRequest("Customer1", "c1@mail.com", Optional.of(new ProjectRequest("project1", "project1"))))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(CustomerResponse.class)
                .value( customerResponse -> {
                    assertThat(customerResponse.getId()).isNotNull();
                    assertThat(customerResponse.getName()).isEqualTo("Customer1");
                    assertThat(customerResponse.getCreationDate()).isEqualTo(LocalDate.now());
                    assertThat(customerResponse.getContact()).isEqualTo("c1@mail.com");
                    assertThat(customerResponse.getProjects()).hasSize(1).extracting(ProjectResponse::getName).containsExactly("project1");
                });
    }


    @Test
    void testSaveCustomerHasProjectWithNew(){
        webTestClient.post()
                .uri("/api/customer/project")
                .bodyValue(new CustomerRequest("Customer1", "c1@mail.com", Optional.of(new ProjectRequest("project1", "project1"))))
                .exchange()
                .expectStatus().is2xxSuccessful();

        webTestClient.post()
                .uri("/api/customer/project")
                .bodyValue(new CustomerRequest("Customer1", "c1@mail.com", Optional.of(new ProjectRequest("project2", "project2"))))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(CustomerResponse.class)
                .value( customerResponse -> {
                    assertThat(customerResponse.getId()).isNotNull();
                    assertThat(customerResponse.getName()).isEqualTo("Customer1");
                    assertThat(customerResponse.getCreationDate()).isEqualTo(LocalDate.now());
                    assertThat(customerResponse.getContact()).isEqualTo("c1@mail.com");
                    assertThat(customerResponse.getProjects()).hasSize(2).extracting(ProjectResponse::getName).containsExactlyInAnyOrder("project1", "project2");
                });
    }
}
