package com.example.spacebidder.service;

import com.example.spacebidder.Cart;
import com.example.spacebidder.dto.OrderDto;
import com.example.spacebidder.mapper.OrderMapper;
import com.example.spacebidder.model.order.Order;
import com.example.spacebidder.repository.order.OrderItemRepository;
import com.example.spacebidder.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final Cart cart;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(Cart cart, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public Long saveOrder(OrderDto orderDto) {
        Order order = OrderMapper.mapToOrder(orderDto);
        Order o = orderRepository.save(order);
        orderItemRepository.saveAll(OrderMapper.mapToOrderItemList(cart, order));
        cart.cleanCart();
        return o.getOrderId();
    }
}
