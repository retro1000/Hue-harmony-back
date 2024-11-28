package hueHarmony.web.repository;

import hueHarmony.web.model.SupplierVariation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierVariationRepository extends JpaRepository<SupplierVariation, Long> {
}
