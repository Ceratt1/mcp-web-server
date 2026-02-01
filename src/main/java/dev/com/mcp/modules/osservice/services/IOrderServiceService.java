package dev.com.mcp.modules.osservice.services;

import java.util.List;

import dev.com.mcp.modules.osservice.models.OrderService;

public interface IOrderServiceService {
    
    List<OrderService> getAll();

    OrderService getByName(String customerName);

    OrderService create(String orderNumber, String customerName);

}
