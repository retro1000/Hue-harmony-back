package hueHarmony.web.repository;

import hueHarmony.web.model.Loyalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface LoyaltyRepository extends JpaRepository<Loyalty, BigDecimal> {
    @Modifying
    @Query("UPDATE Loyalty l SET l.loyaltyPoints = l.loyaltyPoints + :points WHERE l.contactNo = :contactNo")
    int updateLoyaltyPoints(@Param("contactNo") BigDecimal contactNo, @Param("points") Integer points);

    Optional<Object> findByContactNo(BigDecimal contactNo);
}
