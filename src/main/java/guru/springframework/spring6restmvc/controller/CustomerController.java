package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Customer;
import guru.springframework.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PatchMapping("{customerId}")
    public ResponseEntity patchCustomer(@PathVariable UUID customerId, @RequestBody Customer customer) {
        customerService.patchBeerById(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable(name="customerId") UUID customerId) {
        customerService.deleteCustomerById(customerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public ResponseEntity updateCustomer(@PathVariable(name="id") UUID id, @RequestBody Customer customer) {
        customerService.updateCustomerById(id, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @PostMapping
    public ResponseEntity addCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.addCustomer(customer);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "api/v1/customer" + savedCustomer.getId().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @RequestMapping(value = "{customerId}", method = RequestMethod.GET)
    public Customer getCustomerByID(@PathVariable(name = "customerId") UUID customerId) {
        return customerService.getCustomerById(customerId);
    }
}
