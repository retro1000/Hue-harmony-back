package hueHarmony.web.service;

import hueHarmony.web.dto.FilterSupplierDto;
import hueHarmony.web.dto.PurchaseOrderDto;
import hueHarmony.web.dto.response_dto.PurchaseOrderDisplayDto;
import hueHarmony.web.model.PurchaseOrder;
import hueHarmony.web.repository.PurchaseOrderRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Transactional
    public Page<PurchaseOrderDisplayDto> filterPurchaseOrder(FilterSupplierDto dto) {
        Page<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll(PageRequest.of(dto.getPage(), dto.getLimit()));

        if(purchaseOrders.isEmpty()){
            return null;
        }

        return purchaseOrders.map(purchaseOrder -> new PurchaseOrderDisplayDto(
                purchaseOrder.getPurchaseOrderId(),
                purchaseOrder.getDescription(),
                purchaseOrder.getStatus(),
                purchaseOrder.getSupplier().getSupplierName()
        ));
    }

    public PurchaseOrder findPurchaseOrderById(Long id) {
//        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);


        if (purchaseOrder.isEmpty()) {
            throw new IllegalStateException("PurchaseOrder");

        }
        PurchaseOrder savedPurchaseOrder = purchaseOrder.get();
        Hibernate.initialize(savedPurchaseOrder.getSupplier());

        return savedPurchaseOrder;
//        try {
//            throw new Exception("Purchase Order does not exist");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder purchaseOrderRequest) {
        return purchaseOrderRepository.save(purchaseOrderRequest);

    }

    public PurchaseOrder updateOrderStatus(Long id, PurchaseOrder statusRequest) {
        PurchaseOrder purchaseOrder = findPurchaseOrderById(id);

        purchaseOrder.setStatus(statusRequest.getStatus());
        return purchaseOrderRepository.save(purchaseOrder);
    }

    public void deletePurchaseOrder(Long id) {

        PurchaseOrder purchaseOrder = findPurchaseOrderById(id);

        purchaseOrderRepository.delete(purchaseOrder);
    }
}
