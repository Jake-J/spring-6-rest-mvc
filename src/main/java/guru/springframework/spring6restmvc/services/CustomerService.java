package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    Customer addCustomer(Customer customer);

    Customer getCustomerById(UUID id);

    List<Customer> getCustomers();

    void updateCustomerById(UUID id, Customer customer);

    void deleteCustomerById(UUID customerId);

    void patchBeerById(UUID id, Customer customer);
}
