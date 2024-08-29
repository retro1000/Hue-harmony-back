package hueHarmony.web.service;

import hueHarmony.web.model.enums.SupplierStatus;
import hueHarmony.web.repository.SupplierRepository;
import hueHarmony.web.repository.SupplierVariationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierVariationRepository supplierVariationRepository;

    public boolean isSupplierExist(int supplierId){
        return supplierRepository.existsById((long) supplierId);
    }

    public boolean isSupplierVariationExist(int supplierVariationId){
        return supplierVariationRepository.existsById((long) supplierVariationId);
    }

    public boolean checkSupplierStatus(int supplierId, String status){
        return supplierRepository.checkSupplierStatusBySupplierId((long) supplierId);
    }

}
