package hueHarmony.web.service;

import hueHarmony.web.dto.CustomerDto;
import hueHarmony.web.dto.WholeSaleCustomerDto;
import hueHarmony.web.model.Customer;
import hueHarmony.web.model.enums.LinkedCardStatus;
import hueHarmony.web.repository.CustomerRepository;
import hueHarmony.web.repository.LinkedCardRepository;
import hueHarmony.web.repository.RetailCustomerRepository;
import hueHarmony.web.repository.WholeSaleCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RetailCustomerRepository retailCustomerRepository;
    private final WholeSaleCustomerRepository wholeSaleCustomerRepository;
    private final LinkedCardRepository linkedCardRepository;

    public boolean isCustomerExist(int customerId){
        return customerRepository.existsById((long) customerId);
    }

    public boolean isRetailCustomerExist(int retailCustomerId){
        return customerRepository.existsById((long) retailCustomerId);
    }

    public boolean isWholeSaleCustomerExist(int wholeSaleCustomerId){
        return customerRepository.existsById((long) wholeSaleCustomerId);
    }

    public boolean isValidCustomerId(WholeSaleCustomerDto wholeSaleCustomerDto, String key){
        return wholeSaleCustomerRepository.checkCustomerIdAndWholeSaleCustomerIdAreLinked((long) wholeSaleCustomerDto.getCustomerDto().getCustomerId(), (long) wholeSaleCustomerDto.getWholeSaleCustomerId());
    }

    public String findDefaultLinkedCardByCustomerId(int customerId){
        try {
            String paymentMethodId = linkedCardRepository.findDefaultLinkedCardByCustomerId(customerId, LinkedCardStatus.AVAILABLE);
            if(paymentMethodId==null || paymentMethodId.isEmpty() || paymentMethodId.isBlank()) return null;
            return paymentMethodId;
        }catch (Exception exception){
            return null;
        }
    }

    @Transactional
    public void createCustomer(CustomerDto customerDto){
        Customer customer = new Customer();
    }
}
