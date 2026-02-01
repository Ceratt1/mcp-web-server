package dev.com.mcp.modules.osservice.models;

import dev.com.mcp.application.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderService extends BaseEntity {
    
    private String orderNumber;
    private String customerName;

}
