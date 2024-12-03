package hueHarmony.web.repository;

import hueHarmony.web.model.PurchaseOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderProductRepository extends JpaRepository<PurchaseOrderProduct, Integer> {
}
