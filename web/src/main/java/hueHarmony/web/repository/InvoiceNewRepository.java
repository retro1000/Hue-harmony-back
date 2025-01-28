package hueHarmony.web.repository;

import hueHarmony.web.controller.Invoice;
import hueHarmony.web.model.InvoiceNew;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceNewRepository extends JpaRepository<InvoiceNew,Long> {
    List<InvoiceNew> findByCustomerId(Long customerId);
}
