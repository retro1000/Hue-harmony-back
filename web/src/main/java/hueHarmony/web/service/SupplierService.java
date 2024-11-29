package hueHarmony.web.service;

import hueHarmony.web.dto.SupplierDto;
import hueHarmony.web.model.Supplier;
import hueHarmony.web.model.User;
import hueHarmony.web.model.enums.SupplierStatus;
import hueHarmony.web.repository.SupplierRepository;
import hueHarmony.web.util.JwtUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
//    private final SupplierVariationRepository supplierVariationRepository;
    private final JwtUtil jwtUtil;

    @PersistenceContext
    private EntityManager entityManager;

    public boolean isSupplierExist(int supplierId){
        return supplierRepository.existsById((long) supplierId);
    }

//    public boolean isSupplierVariationExist(int supplierVariationId){
//        return supplierVariationRepository.existsById((long) supplierVariationId);
//    }

    public boolean checkSupplierStatus(int supplierId, String status){
        return supplierRepository.checkSupplierStatusBySupplierId((long) supplierId);
    }

    public void createSupplier(SupplierDto supplierDto){
        Supplier supplier = new Supplier();
        supplier.setSupplierName(supplierDto.getSupplierName());
        supplier.setSupplierAddress(supplierDto.getSupplierAddress());
        supplier.setSupplierMobilePhone(supplierDto.getSupplierMobilePhone());
        supplier.setSupplierEmail(supplierDto.getSupplierEmail());
        supplier.setSupplierLandPhone(supplierDto.getSupplierLandPhone());
        supplier.setSupplierType(supplierDto.getSupplierType());
        supplier.setSupplierStatus(
                jwtUtil.extractRoleWithToken().contains("ROLE_ADMIN")?
                        SupplierStatus.ACTIVE :
                        supplierDto.getSupplierStatus()
        );
        supplier.setCreatedUser(entityManager.getReference(User.class, jwtUtil.extractUserIdWithToken()));
        if(jwtUtil.extractRoleWithToken().contains("ROLE_ADMIN")){
            supplier.setApprovedUser(supplier.getCreatedUser());
        }
        supplierRepository.save(supplier);
    }

    public void updateSupplier(SupplierDto supplierDto){
        Supplier supplier = supplierRepository.getReferenceById((long) supplierDto.getSupplierId());

        supplier.setSupplierName(supplierDto.getSupplierName());
        supplier.setSupplierAddress(supplierDto.getSupplierAddress());
        supplier.setSupplierMobilePhone(supplierDto.getSupplierMobilePhone());
        supplier.setSupplierEmail(supplierDto.getSupplierEmail());
        supplier.setSupplierLandPhone(supplierDto.getSupplierLandPhone());
        supplier.setSupplierType(supplierDto.getSupplierType());

        supplierRepository.save(supplier);
    }

    public void deleteSupplier(SupplierDto supplierDto){
        supplierRepository.deleteById((long) supplierDto.getSupplierId());
    }

    public void updateSupplierStatus(SupplierDto supplierDto){
        supplierRepository.updateSupplierStatusBySupplierId((long) supplierDto.getSupplierId(), supplierDto.getSupplierStatus());
    }
}

