package hueHarmony.web.controller;

import hueHarmony.web.dto.SalesOrderProductDto;
import hueHarmony.web.dto.WholeSaleCustomersDto;
import hueHarmony.web.service.WholeSaleCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wholesalecustomer")
@RequiredArgsConstructor
public class WholeSaleCustomerController {

    private final WholeSaleCustomerService wholeSaleCustomerService;

    // Endpoint to get all wholesale customers
    @GetMapping("/get-all")
    public Iterable<WholeSaleCustomersDto> getAllCustomers() {
        return wholeSaleCustomerService.getAllCustomers();
    }

    @GetMapping("/get-products")
    public Iterable<SalesOrderProductDto> getAllProducts() {
        return wholeSaleCustomerService.getAllProducts();
    }

    // Endpoint to update customer status
//    @PutMapping("/{id}/status")
//    public ResponseEntity<WholeSaleCustomer> updateCustomerStatus(@PathVariable Long id, @RequestParam String status) {
//        WholeSaleCustomer updatedCustomer = wholeSaleCustomerService.updateCustomerStatus(id, status);
//        return ResponseEntity.ok(updatedCustomer);
//    }
//
//    // Endpoint to get a particular customer by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<WholeSaleCustomer> getCustomerById(@PathVariable Long id) {
//        WholeSaleCustomer customer = wholeSaleCustomerService.getCustomerById(id);
//        return ResponseEntity.ok(customer);
//    }


}
