package hueHarmony.web.repository;

import hueHarmony.web.model.PosOrder;
import hueHarmony.web.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PosOrderRepository extends JpaRepository<PosOrder,Long> {

    List<PosOrder> findByCashierIdAndOrderDateBetween(Long cashierId, LocalDateTime startDate, LocalDateTime endDate);

    List<PosOrder> findByCashierIdAndOrderStatus(Long cashierId, OrderStatus orderStatus);
}
