package hueHarmony.web.repository;

import PROJ.VIVO.model.DeliveryServiceDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryServiceDistrictRepository extends JpaRepository<DeliveryServiceDistrict, Long> {

    @Query(value = "SELECT dsd.deliverCharge FROM DeliveryServiceDistrict dsd WHERE dsd.deliveryService.deliveryServiceId = :serId AND dsd.district.districtId = :disId")
    float getDeliverChargeByDistrictAndDeliveryService(@Param("disId")long disId, @Param("serId")long serId);
}
