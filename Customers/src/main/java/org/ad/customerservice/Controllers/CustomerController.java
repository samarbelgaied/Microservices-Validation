package org.ad.customerservice.Controllers;

import org.ad.customerservice.Entities.CustomerEntity;
import org.ad.customerservice.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService ;

    @GetMapping("/customers")
    private List<CustomerEntity> getAllCustomers()
    {
        return customerService.getAllCustomers();
    }
    @GetMapping("/customers/{customerId}")
    private CustomerEntity getCustomer(@PathVariable("customerId") Long customerId)
    {
        return customerService.getCustomerById(customerId);
    }
    @DeleteMapping("/customers/{customerId}")
    private void deleteCustomer(@PathVariable("customerId") Long customerId)
    {
        customerService.delete(customerId);
    }
    @PostMapping("/customers")
    private Long saveCustomer(@RequestBody CustomerEntity customer)
    {
        customerService.saveOrUpdate(customer);
        return customer.getId();
    }
    @PutMapping("/customers")
    private CustomerEntity update(@RequestBody CustomerEntity customer)
    {
        customerService.saveOrUpdate(customer);
        return customer;
    }
}
