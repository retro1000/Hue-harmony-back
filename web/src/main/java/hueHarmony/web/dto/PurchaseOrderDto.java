package hueHarmony.web.dto;

import hueHarmony.web.model.PurchaseOrder;
import hueHarmony.web.model.PurchaseOrderProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDto {

    private long id;
    private String name;
    private List<SupplierProductFrontDto> products;
//    private String description;
//    private SupplierDto supplier;
//    private List<PurchaseOrderProduct> purchaseOrderProducts;

//    public PurchaseOrderDto(long id, String name, List<SupplierProductFrontDto> products) {
//        this.id = id;
//        this.name = name;
//        this.products = products;

//    }
}


