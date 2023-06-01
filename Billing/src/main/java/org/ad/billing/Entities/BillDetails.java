package org.ad.billing.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BillDetails {
    private Long id ;
    private CustomerEntity customer ;
    private List<Product> products ;
    private Long finalPricing ;

}
