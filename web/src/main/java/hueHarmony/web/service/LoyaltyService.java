package hueHarmony.web.service;

import hueHarmony.web.dto.LoyaltyDto;
import hueHarmony.web.model.Loyalty;
import hueHarmony.web.repository.LoyaltyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoyaltyService {

    private LoyaltyRepository loyaltyRepository;

    // Retrieve loyalty details
    public LoyaltyDto getLoyaltyDetails(String contactNo) {
        Optional<Loyalty> loyaltyOptional = loyaltyRepository.findById(contactNo);
        if (loyaltyOptional.isPresent()) {
            Loyalty loyalty = loyaltyOptional.get();
            return new LoyaltyDto(
                    loyalty.getContactNo(),
                    loyalty.getLoyaltyStatus(),
                    loyalty.getLoyaltyPoints()
            );
        }
        throw new IllegalArgumentException("Loyalty details not found for contact number: " + contactNo);
    }

    // Add or update loyalty details
//    public LoyaltyDto saveLoyaltyDetails(LoyaltyDto loyaltyDto) {
//        Loyalty loyalty = new Loyalty(
//                loyaltyDto.getContactNo(),
//                loyaltyDto.getLoyaltyStatus(),
//                loyaltyDto.getLoyaltyPoints()
//        );
//        Loyalty savedLoyalty = loyaltyRepository.save(loyalty);
//        return new LoyaltyDto(
//                savedLoyalty.getContactNo(),
//                savedLoyalty.getLoyaltyStatus(),
//                savedLoyalty.getPoints()
//        );
//    }

    // Delete loyalty by contact number
    public void deleteLoyalty(String contactNo) {
        loyaltyRepository.deleteById(contactNo);
    }
}
