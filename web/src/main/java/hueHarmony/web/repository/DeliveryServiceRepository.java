package hueHarmony.web.repository;

import hueHarmony.web.model.DeliveryService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface DeliveryServiceRepository extends JpaRepository<DeliveryService, Long> {
}
