package hueHarmony.web.service;

import hueHarmony.web.dto.PosProductDto;
import hueHarmony.web.dto.SummaryResponseDto;
import hueHarmony.web.model.PosOrder;
import hueHarmony.web.model.enums.OrderStatus;
import hueHarmony.web.repository.PosOrderRepository;
import hueHarmony.web.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PosService {

    private final ProductRepository productRepository;
    private final PosOrderRepository posOrderRepository;

    public List<PosProductDto> getProducts() {
        return productRepository.getProducts();
    }

    public PosOrder createOrder(PosOrder order) {
        // Add any additional logic here if needed, like validating the order, applying discounts, etc.
        return posOrderRepository.save(order); // Save order to the database
    }

    public SummaryResponseDto getTotalsForCashierOnDate(Long cashierId, LocalDate parsedDate) {
        // Use the parsedDate to get the start and end of the day
        LocalDateTime startOfDay = parsedDate.atStartOfDay();
        LocalDateTime endOfDay = parsedDate.atTime(23, 59, 59);

        // Fetch orders for the cashier on the given date
        List<PosOrder> orders = posOrderRepository.findByCashierIdAndOrderDateBetween(cashierId, startOfDay, endOfDay);

        // Initialize totals
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal cashTotal = BigDecimal.ZERO;
        BigDecimal cardTotal = BigDecimal.ZERO;
        BigDecimal discountTotal = BigDecimal.ZERO;

        // Loop through orders and calculate totals
        for (PosOrder order : orders) {
            total = total.add(order.getTotal());
            discountTotal = discountTotal.add(order.getDiscount());

            if ("Cash".equalsIgnoreCase(order.getPaymentMethod())) {
                cashTotal = cashTotal.add(order.getTotal());
            } else if ("Card".equalsIgnoreCase(order.getPaymentMethod())) {
                cardTotal = cardTotal.add(order.getTotal());
            }
        }

        // Return the totals in a response object
        return new SummaryResponseDto(total, cashTotal, cardTotal, discountTotal);
    }

    public List<PosOrder> getCompletedOrdersByCashier(Long cashierId) {
        // Fetch completed orders for the cashier
        return posOrderRepository.findByCashierIdAndOrderStatus(cashierId, OrderStatus.COMPLETED);
    }

    public Optional<PosOrder> getOrderById(Long orderId) {
        // Fetch the order by its ID, returns Optional to handle cases where the order might not exist
        return posOrderRepository.findById(orderId);
    }
}
