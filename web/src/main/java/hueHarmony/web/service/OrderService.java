package hueHarmony.web.service;

import hueHarmony.web.dto.OrderDto;
import hueHarmony.web.model.Order;
import hueHarmony.web.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder(OrderDto orderDto){
        Order order = new Order();

        orderRepository.save(order);
    }
}
