package hueHarmony.web.repository;

import hueHarmony.web.model.WholeSaleCustomer;
import hueHarmony.web.model.WholeSaleOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WholeSaleOrderRepository extends JpaRepository<WholeSaleOrder,Long> {


    public List<WholeSaleOrder> findByCustomer(WholeSaleCustomer customer);
}
