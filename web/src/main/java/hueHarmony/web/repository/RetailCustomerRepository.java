package hueHarmony.web.repository;

import hueHarmony.web.model.RetailCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailCustomerRepository extends JpaRepository<RetailCustomer, Long> {
}
