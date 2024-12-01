package hueHarmony.web.controller;

import hueHarmony.web.dto.WholeSaleOrderdto;
import hueHarmony.web.model.WholeSaleOrder;
import hueHarmony.web.service.WholeSaleOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wholeSale")
@RequiredArgsConstructor
public class WholeSaleOrderController {

    private final WholeSaleOrderService wholeSaleOrderService;

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody WholeSaleOrderdto wholeSaleOrderDto) {
        try {
            // Step 1: Convert DTO to Entity
//            WholeSaleOrder wholeSaleOrder = convertToEntity(wholeSaleOrderdto);

            // Step 2: Call the service to create the order
             wholeSaleOrderService.createOrder(wholeSaleOrderDto);

            // Step 3: Return success response with created order
            return  ResponseEntity.status(HttpStatus.CREATED).body("Order Created");
        } catch (Exception e) {
            // Handle exception and return error response
            return new ResponseEntity<>("Error creating order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
