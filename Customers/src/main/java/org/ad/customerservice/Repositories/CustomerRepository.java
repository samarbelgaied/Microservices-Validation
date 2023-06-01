package org.ad.customerservice.Repositories;

import org.ad.customerservice.Entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
