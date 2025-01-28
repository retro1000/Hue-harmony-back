package hueHarmony.web.service;

import hueHarmony.web.dto.FilterSupplierDto;
import hueHarmony.web.dto.PurchaseOrderDto;
import hueHarmony.web.dto.response_dto.PurchaseOrderDisplayDto;
import hueHarmony.web.model.PurchaseOrder;
import hueHarmony.web.dto.PurchaseOrderDto;
import hueHarmony.web.dto.PurchaseOrderProductDto;
import hueHarmony.web.dto.SupplierDto;
import hueHarmony.web.dto.SupplierProductFrontDto;
import hueHarmony.web.model.*;
import hueHarmony.web.repository.PurchaseOrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public PurchaseOrder savePurchaseOrder(PurchaseOrderDto purchaseOrderDto) {
//        return purchaseOrderRepository.save(purchaseOrder);

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setDescription(purchaseOrderDto.getDescription());
        purchaseOrder.setSupplier(entityManager.getReference(Supplier.class, purchaseOrderDto.getSupplier().getSupplierId()));

        List<PurchaseOrderProduct> purchaseOrderProducts = purchaseOrderDto.getPurchaseOrderProduct()
                .stream()
                .map(productDto -> {
            PurchaseOrderProduct product = new PurchaseOrderProduct();
            product.setPurchaseOrder(purchaseOrder);
            product.setProduct(entityManager.getReference(Product.class,productDto.getProductId()));
            product.setQuantity(productDto.getQuantity());
            return product;
        }).collect(Collectors.toList());

        purchaseOrder.setPurchaseOrderProduct(purchaseOrderProducts);
//
//        // Save the PurchaseOrder along with its related products
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
        PurchaseOrder savedPurchaseOrder = purchaseOrderRepository.findByPurchaseOrderId(id);


//        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);


        if (purchaseOrder.isEmpty()) {
            throw new IllegalStateException("PurchaseOrder");

        }
//        PurchaseOrder savedPurchaseOrder = purchaseOrder.get();
//        Hibernate.initialize(savedPurchaseOrder.getSupplier());

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

    public List<PurchaseOrderDto> getAllPurchaseOrderSummaries() {
        return purchaseOrderRepository.findAll()
                .stream()
                .map(order -> new PurchaseOrderDto(
                        order.getPurchaseOrderId(),
                        order.getPurchaseOrderProduct().stream()
                                .map(product -> new PurchaseOrderProductDto(
                                        product.getProduct().getProductId(),
                                        product.getQuantity()
                                ))
                                .collect(Collectors.toList()),
                        order.getDescription()))
                .collect(Collectors.toList());
    }
}
