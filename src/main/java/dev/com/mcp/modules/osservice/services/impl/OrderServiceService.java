package dev.com.mcp.modules.osservice.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import dev.com.mcp.modules.osservice.models.OrderService;
import dev.com.mcp.modules.osservice.services.IOrderServiceService;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceService implements IOrderServiceService {
    

    List<OrderService> orderServices = new ArrayList<>();


    @PostConstruct
    public void init(){
        orderServices.add(new OrderService("1", "Customer A"));
        orderServices.add(new OrderService("2", "Customer B"));
        orderServices.add(new OrderService("3", "Customer C"));
    }


    @Tool(name="get_all_order_services", description="Get a list of all order services")
    @Override
    public List<OrderService> getAll() {
     
        log.info("OrderServiceService getAll method called");
        orderServices.forEach(orderService -> log.info(orderService.getCustomerName()));
        return orderServices;
    }


    @Tool(name="get_order_service_by_name", description="Get an order service by customer name")
    @Override
    public OrderService getByName(String customerName) {
        return orderServices.stream()
                .filter(orderService -> orderService.getCustomerName().equalsIgnoreCase(customerName))
                .findFirst()
                .orElse(null);
    }


    @Tool(name="create_order_service", description="Create a new order service, given an order number and customer name    OrderService getByName(String customerName);\n" + //
                "")
    @Override
    public OrderService create(String orderNumber, String customerName) {
        OrderService newOrderService = new OrderService(orderNumber, customerName);
        orderServices.add(newOrderService);
        return newOrderService;
    }


    
}
