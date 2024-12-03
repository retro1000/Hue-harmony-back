package hueHarmony.web.service;

import hueHarmony.web.dto.SalesOrderProductDto;
import hueHarmony.web.dto.WholeSaleCustomerDto;
import hueHarmony.web.dto.WholeSaleCustomersDto;
import hueHarmony.web.model.SalesOrderProductResponseDto;
import hueHarmony.web.model.WholeSaleCustomer;
import hueHarmony.web.repository.ProductRepository;
import hueHarmony.web.repository.WholeSaleCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WholeSaleCustomerService {

    private final WholeSaleCustomerRepository wholeSaleCustomerRepository;
    private final ProductRepository productRepository;


    private WholeSaleCustomersDto convertToDTO(WholeSaleCustomer customer) {
        return  WholeSaleCustomersDto.builder()
                .customerId(customer.getWholeSaleCustomerId())
                .firstName(customer.getCustomer().getFirstName())
                .lastName(customer.getCustomer().getLastName())
                .businessAddress(customer.getBusinessAddress())
                .shippingAddress(customer.getDeliveryAddress())  // Assuming deliveryAddress is the shipping address
                .contactNo(customer.getLandPhone())
                //.setContactPersonNumber(customer.getContactPersonNumber())
                .emailAddress(customer.getCustomer().getEmail())  // Assuming email is the field name
                //.setTotalPurchases(customer.getTotalPurchases())
                .status(customer.getWholeSaleCustomerStatus())
                .build();
    }


    public List<WholeSaleCustomersDto> getAllCustomers() {
        return wholeSaleCustomerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Iterable<SalesOrderProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> SalesOrderProductDto.builder()
                        .product(product.getProductId())
                        .fullPrice(product.getProductPrice())// Example of mapping fields
                        .build()
                )
                .collect(Collectors.toList());
    }
}
