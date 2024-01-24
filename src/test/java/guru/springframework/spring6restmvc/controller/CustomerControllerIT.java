package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.entities.Customer;
import guru.springframework.spring6restmvc.mappers.CustomerMapper;
import guru.springframework.spring6restmvc.model.BeerDTO;
import guru.springframework.spring6restmvc.model.CustomerDTO;
import guru.springframework.spring6restmvc.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CustomerControllerIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerMapper customerMapper;

    @Test
    @Transactional
    @Rollback
    void deleteCustomerByIdFound() {
        Customer customer = customerRepository.findAll().get(0);
        ResponseEntity responseEntity = customerController.deleteCustomer(customer.getId());

        assertThat(customerRepository.findById(customer.getId()).isEmpty());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
    }

    @Test
    @Transactional
    @Rollback
    void deleteCustomerByIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.deleteCustomer(UUID.randomUUID());
        });
    }

    @Test
    @Transactional
    @Rollback
    void updateCustomerNotFound() {
        assertThrows(NotFoundException.class , () -> {
            customerController.updateCustomer(UUID.randomUUID(), CustomerDTO.builder().build());
        });
    }

    @Test
    @Transactional
    @Rollback
    void updateExistingCustomer() {
        Customer customer = customerRepository.findAll().get(0);
        customer.setCustomerName("Marco Polo");
        ResponseEntity responseEntity = customerController.updateCustomer(
                customer.getId(), customerMapper.customerToCustomerDto(customer)
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
    }

    @Test
    @Transactional
    @Rollback
    void saveNewCustomer() {
        CustomerDTO customer1 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName("Evans")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        ResponseEntity responseEntity = customerController.addCustomer(customer1);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        assertThat(customerRepository.findById(customer1.getId())).isNotNull();
    }

    @Test
    void getCustomerByIdNotFound() {
        assertThrows(NotFoundException.class , () -> {
            customerController.getCustomerByID(UUID.randomUUID());
        });
    }

    @Test
    void getCustomerById() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO dto = customerController.getCustomerByID(customer.getId());

        assertThat(dto).isNotNull();
    }

    @Transactional
    @Rollback
    @Test
    void getEmptyList() {
        customerRepository.deleteAll();
        List<CustomerDTO> customers= customerController.getCustomers();

        assertThat(customers.size()).isEqualTo(0);

    }

    @Test
    void getCustomerList() {
        List<CustomerDTO> customers= customerController.getCustomers();

        assertThat(customers.size()).isEqualTo(3);
    }

    @Test
    void testPatchBeerNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.patchCustomer(UUID.randomUUID(), CustomerDTO.builder().build());
        });
    }


    @Rollback
    @Transactional
    @Test
    void patchExistingBeer() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);
        customerDTO.setId(null);
        customerDTO.setVersion(null);
        final String customerName = "UPDATED";
        customerDTO.setCustomerName(customerName);

        ResponseEntity responseEntity = customerController.patchCustomer(customer.getId(), customerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer patchedBeer = customerRepository.findById(customer.getId()).get();
        assertThat(patchedBeer.getCustomerName()).isEqualTo(customerName);
    }


}