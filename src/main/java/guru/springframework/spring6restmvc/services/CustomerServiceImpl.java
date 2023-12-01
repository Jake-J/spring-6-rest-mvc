package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.CustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    Map<UUID, CustomerDTO> customerMap;

    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();

        CustomerDTO customer1 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName("Evans")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();


        CustomerDTO customer2 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName("Balan")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();


        CustomerDTO customer3 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName("Mario")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO customer) {
        CustomerDTO newCustomer = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName(customer.getCustomerName())
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(newCustomer.getId(), newCustomer);

        return newCustomer;
    }

    @Override
    public CustomerDTO getCustomerById(UUID id) {
        return customerMap.get(id);
    }

    @Override
    public List<CustomerDTO> getCustomers() {

        return new ArrayList<>(customerMap.values());
    }

    @Override
    public void updateCustomerById(UUID id, CustomerDTO customer) {
        CustomerDTO existing = customerMap.get(id);

        existing.setCustomerName(customer.getCustomerName());
        existing.setLastModifiedDate(LocalDateTime.now());

        customerMap.put(id, existing);
    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        customerMap.remove(customerId);
    }

    @Override
    public void patchBeerById(UUID id, CustomerDTO customer) {
        CustomerDTO existing = customerMap.get(id);

        if (StringUtils.hasText(customer.getCustomerName())) {
            existing.setCustomerName(customer.getCustomerName());
        }

        existing.setLastModifiedDate(LocalDateTime.now());

        customerMap.put(id, existing);
    }
}
