package bkris.project_customer_application;

import org.springframework.boot.SpringApplication;

public class TestCustomerProjectApplication {

    public static void main(String[] args) {
        SpringApplication.from(ProjectCustomerApplication::main).with(TestDatabaseConfiguration.class).run(args);
    }
}
