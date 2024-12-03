package hueHarmony.web.controller;

import hueHarmony.web.model.PurchaseOrderProduct;
import hueHarmony.web.service.PurchaseOrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchase-order-products")
public class PurchaseOrderProductController {

    @Autowired
    private PurchaseOrderProductService purchaseOrderProductService;

    @GetMapping
    public List<PurchaseOrderProduct> getAllPurchaseOrderProducts() {
        try{
            return purchaseOrderProductService.getAllPurchaseOrderProducts();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
