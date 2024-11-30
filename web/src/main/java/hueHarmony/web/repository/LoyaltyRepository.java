package hueHarmony.web.repository;

import hueHarmony.web.model.Loyalty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyRepository extends JpaRepository<Loyalty, String> {
}
