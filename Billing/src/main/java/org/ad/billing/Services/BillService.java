package org.ad.billing.Services;

import org.ad.billing.Entities.Bill;
import org.ad.billing.Entities.CustomerEntity;
import org.ad.billing.Entities.Product;
import org.ad.billing.Repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {

    @Autowired
    BillRepository billRepository ;

    @Value("${app.products.service.uri}")
    private String productsServiceUri;

    @Value("${app.customers.service.uri}")
    private String customerServiceUri;

    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        billRepository.findAll().forEach(bill -> bills.add(bill));
        return  bills ;
    }

    public Bill getBillById(Long id)
    {
        return billRepository.findById(id).get();
    }

    public void saveOrUpdate(Bill bill)
    {
        billRepository.save(bill);
    }

    public void delete(Long id)
    {
        billRepository.deleteById(id);
    }

    public void update(Bill bill, Long id)
    {
        billRepository.save(bill);
    }

    public Long getBillFinalPricing(Bill bill) {
        Long finalPricing = Long.valueOf(0);
        List<String> Ids = new ArrayList<>() ;
        List<Product> products = getProductsDetails(bill.getProductsList());
        for (Product product : products ) {
            finalPricing = finalPricing + Long.parseLong(product.getPrice());
        }
        return finalPricing ;
    }

    public List<Product> getProductsDetails(List<String> Ids) {
        RestTemplate restTemplate = new RestTemplate();
        List<Product> products = new ArrayList<>();

        for (String id : Ids) {
            Product product = restTemplate.getForObject(productsServiceUri + id , Product.class);
            products.add(product);
        }

        return products;
    }

    public CustomerEntity getCustomerDetails (Long id) {
        RestTemplate restTemplate = new RestTemplate() ;
        return restTemplate.getForObject(customerServiceUri + id.toString() , CustomerEntity.class);
    }

}
