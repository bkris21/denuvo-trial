package bkris.project_customer_application.mappers;

import bkris.project_customer_application.dtos.customer.CustomerResponse;
import bkris.project_customer_application.entities.CustomerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerProjectMapper {
    CustomerResponse mapToCustomerResponse(CustomerEntity customerEntity);

    List<CustomerResponse> mapToCustomerListResponse(List<CustomerEntity> customerEntities);
}
