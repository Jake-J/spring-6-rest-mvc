package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Customer;
import guru.springframework.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    private final CustomerService customerService;

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity patchCustomer(@PathVariable UUID customerId, @RequestBody Customer customer) {
        customerService.patchBeerById(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity deleteCustomer(@PathVariable(name="customerId") UUID customerId) {
        customerService.deleteCustomerById(customerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity updateCustomer(@PathVariable UUID customerId, @RequestBody Customer customer) {
        customerService.updateCustomerById(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity addCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.addCustomer(customer);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "api/v1/customer" + savedCustomer.getId().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping(CUSTOMER_PATH)
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping(CUSTOMER_PATH_ID)
    public Customer getCustomerByID(@PathVariable UUID customerId) {
        return customerService.getCustomerById(customerId);
    }
}
