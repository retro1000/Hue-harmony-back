package hueHarmony.web.service;

import hueHarmony.web.repository.VariationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VariationService {

    private final VariationRepository variationRepository;

    public boolean isVariationExist(int supplierId){
        return variationRepository.existsById((long) supplierId);
    }

}
