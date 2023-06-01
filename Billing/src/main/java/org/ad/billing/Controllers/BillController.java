package org.ad.billing.Controllers;

import org.ad.billing.Entities.Bill;
import org.ad.billing.Entities.BillDetails;
import org.ad.billing.Services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillController {

    @Autowired
    BillService billService ;

    @GetMapping("/bills")
    private List<Bill> getAllBills()
    {
        return billService.getAllBills();
    }
    //creating a get mapping that retrieves the detail of a specific book
    @GetMapping("/bills/{billId}")
    private Bill getBill(@PathVariable("billId") Long billId)
    {
        return billService.getBillById(billId);
    }
    //creating a delete mapping that deletes a specified book
    @DeleteMapping("/bills/{billId}")
    private void deleteBill(@PathVariable("billId") Long billId)
    {
        billService.delete(billId);
    }
    //creating post mapping that post the book detail in the database
    @PostMapping("/bills")
    private Long saveBill(@RequestBody Bill bill)
    {
        billService.saveOrUpdate(bill);
        return bill.getId();
    }
    //creating put mapping that updates the book detail
    @PutMapping("/bills")
    private Bill update(@RequestBody Bill bill)
    {
        billService.saveOrUpdate(bill);
        return bill;
    }

    @GetMapping("/bills/details/{billId}")
    private BillDetails getBillDetails(@PathVariable("billId") Long billId) {
        BillDetails billDetails = new BillDetails();
        Bill bill = getBill(billId);

        billDetails.setId(billId);
        billDetails.setCustomer(billService.getCustomerDetails(bill.getCustomerId()));
        billDetails.setProducts(billService.getProductsDetails(bill.getProductsList()));
        billDetails.setFinalPricing(billService.getBillFinalPricing(bill));

        return billDetails;
    }
}
