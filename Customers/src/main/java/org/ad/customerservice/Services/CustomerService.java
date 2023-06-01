package org.ad.customerservice.Services;

import org.ad.customerservice.Entities.CustomerEntity;
import org.ad.customerservice.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository ;

    public List<CustomerEntity> getAllCustomers() {
        List<CustomerEntity> cusstomers = new ArrayList<>();
        customerRepository.findAll().forEach(customer -> cusstomers.add(customer));
        return  cusstomers ;
    }

    public CustomerEntity getCustomerById(Long id)
    {
        return customerRepository.findById(id).get();
    }

    public void saveOrUpdate(CustomerEntity customer)
    {
        customerRepository.save(customer);
    }

    public void delete(Long id)
    {
        customerRepository.deleteById(id);
    }

    public void update(CustomerEntity bill, Long id)
    {
        customerRepository.save(bill);
    }

}
