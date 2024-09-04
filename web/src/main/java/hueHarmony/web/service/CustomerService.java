package hueHarmony.web.service;

import hueHarmony.web.dto.CustomerDto;
import hueHarmony.web.dto.WholeSaleCustomerDto;
import hueHarmony.web.model.Customer;
import hueHarmony.web.repository.CustomerRepository;
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

    @Transactional
    public void createCustomer(CustomerDto customerDto){
        Customer customer = new Customer();
    }
}
