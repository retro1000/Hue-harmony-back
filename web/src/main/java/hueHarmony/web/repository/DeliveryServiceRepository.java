package hueHarmony.web.repository;

import hueHarmony.web.model.DeliveryService;
import hueHarmony.web.model.enums.DeliveryServiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryServiceRepository extends JpaRepository<DeliveryService, Long> {

    @Query(value = "SELECT ds.deliveryServiceId FROM DeliveryService ds WHERE ds.deliveryServiceName = :name")
    int findDeliveryServiceIdByName(@Param("name") String name);

//    @Modifying
//    @Query(value = "UPDATE delivery_service ds " +
//            "SET delivery_service_cancel_orders = ds.delivery_service_cancel_orders+1, " +
//            "delivery_service_quality = (ds.delivery_service_cancel_orders+1 / ds.delivery_service_orders)*100 " +
//            "FROM orders o WHERE o.delivery_service_id = ds.delivery_service_id AND o.order_id = :orderId", nativeQuery = true)
//    void updateDeliveryServiceQualityByOrderId(@Param("orderId") int id);

    @Modifying
    @Query("UPDATE DeliveryServiceDistrict dsd SET dsd.deliverCharge = :rate WHERE dsd.deliveryService.deliveryServiceId = :deliveryServiceId AND dsd.district.districtId = :districtId")
    void updateDistrictRateByDeliveryServiceIdAndDistrictId(@Param("districtId") int districtId, @Param("deliveryServiceId") int deliveryServiceId, @Param("rate") float rate);

    @Modifying
    @Query("UPDATE DeliveryService ds SET ds.deliveryServiceStatus = :status WHERE ds.deliveryServiceId = :deliveryServiceId")
    void updateDeliveryServiceStatusByDeliveryServiceIdAndStatus(@Param("deliveryServiceId") int deliveryServiceId, @Param("status") DeliveryServiceStatus status);

    @Modifying
    @Query("UPDATE DeliveryService ds SET ds.deliveryServiceName = :name, ds.deliveryServiceHotline = :hotline WHERE ds.deliveryServiceId = :deliveryServiceId")
    void updateNameAndHotlineByDeliveryServiceId(@Param("deliveryServiceId") int deliveryServiceId, @Param("name") String name, @Param("hotline") String hotline);

    @Modifying
    @Query("UPDATE DeliveryService ds SET ds.pricePerExtraKg = :rate WHERE ds.deliveryServiceId = :deliveryServiceId")
    void updateRatePerKiloByDeliveryServiceId(@Param("deliveryServiceId") int deliveryServiceId, @Param("rate") float rate);

    @Query("SELECT COALESCE(ds.deliveryServiceId, 0) FROM DeliveryService ds WHERE ds.deliveryServiceName = :deliveryServiceName")
    Integer getDeliveryServiceIdByDeliveryServiceName(@Param("deliveryServiceName") String deliveryServiceName);

    boolean existsByDeliveryServiceName(String name);

    @Query("SELECT COALESCE(ds.deliveryServiceId, 0) FROM DeliveryService ds WHERE ds.deliveryServiceHotline = :deliveryServiceHotline")
    Integer getDeliveryServiceIdByDeliveryServiceHotline(@Param("deliveryServiceHotline") String deliveryServiceHotline);

    boolean existsByDeliveryServiceHotline(String deliveryServiceHotline);
}
