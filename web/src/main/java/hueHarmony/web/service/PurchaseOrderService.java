package hueHarmony.web.service;

import hueHarmony.web.dto.PurchaseOrderDto;
import hueHarmony.web.dto.SupplierProductFrontDto;
import hueHarmony.web.model.*;
import hueHarmony.web.repository.PurchaseOrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder) {
//        return purchaseOrderRepository.save(purchaseOrder);

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setDescription(purchaseOrderDto.getDescription());
        purchaseOrder.setSupplier(entityManager.getReference(Supplier.class, purchaseOrderDto.getSupplier().getSupplierId()));

        List<PurchaseOrderProduct> purchaseOrderProducts = purchaseOrderDto.getPurchaseOrderProducts()
                .stream()
                .map(productDto -> {
            PurchaseOrderProduct product = new PurchaseOrderProduct();
            product.setPurchaseOrder(purchaseOrder);
            product.setProduct(entityManager.getReference(Product.class,productDto.getId()));
            product.setQuantity(productDto.getQuantity());
            return product;
        }).collect(Collectors.toList());

        purchaseOrder.setPurchaseOrderProduct(purchaseOrderProducts);
//
//        // Save the PurchaseOrder along with its related products
        return purchaseOrderRepository.save(purchaseOrder);
    }

    public PurchaseOrder findPurchaseOrderById(Long id) {
        PurchaseOrder savedPurchaseOrder = purchaseOrderRepository.findByPurchaseOrderId(id);


        Hibernate.initialize(savedPurchaseOrder.getSupplier());

        return savedPurchaseOrder;
//
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

    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }
}
