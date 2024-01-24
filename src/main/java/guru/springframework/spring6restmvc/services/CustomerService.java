package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    CustomerDTO addCustomer(CustomerDTO customer);

    Optional<CustomerDTO> getCustomerById(UUID id);

    List<CustomerDTO> getCustomers();

    Optional<CustomerDTO> updateCustomerById(UUID id, CustomerDTO customer);

    boolean deleteCustomerById(UUID customerId);

    Optional<CustomerDTO> patchCustomerById(UUID id, CustomerDTO customer);
}
