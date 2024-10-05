package bkris.project_customer_application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestDatabaseConfiguration.class)
@SpringBootTest
class ProjectCustomerApplicationTests {

	@Test
	void contextLoads() {
	}

}
