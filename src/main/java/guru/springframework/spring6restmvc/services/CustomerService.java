package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CustomerDTO addCustomer(CustomerDTO customer);

    CustomerDTO getCustomerById(UUID id);

    List<CustomerDTO> getCustomers();

    void updateCustomerById(UUID id, CustomerDTO customer);

    void deleteCustomerById(UUID customerId);

    void patchBeerById(UUID id, CustomerDTO customer);
}
