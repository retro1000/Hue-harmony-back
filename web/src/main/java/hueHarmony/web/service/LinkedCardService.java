package hueHarmony.web.service;

import hueHarmony.web.model.Customer;
import hueHarmony.web.model.enums.LinkedCardStatus;
import hueHarmony.web.repository.LinkedCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LinkedCardService {

    private final LinkedCardRepository linkedCardRepository;

    @Transactional
    public String handleLinkedCard(String paymentMethodId, Customer customer){

        if(paymentMethodId==null){

        }

        return paymentMethodId!=null && !paymentMethodId.isEmpty() && !paymentMethodId.isBlank() ?
                linkedCardRepository.findLinkedCardByCustomerIdAndPaymentMethodId(customerId, paymentMethodId, LinkedCardStatus.AVAILABLE) :
                linkedCardRepository.findDefaultLinkedCardByCustomerId(customerId, LinkedCardStatus.AVAILABLE);
    }
}
