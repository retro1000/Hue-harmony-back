package hueHarmony.web.service;

import hueHarmony.web.model.PurchaseOrderProduct;
import hueHarmony.web.repository.PurchaseOrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderProductService {

    @Autowired
    private PurchaseOrderProductRepository purchaseOrderProductRepository;

    public List<PurchaseOrderProduct> getAllPurchaseOrderProducts() {
        return purchaseOrderProductRepository.findAll();
    }
}
