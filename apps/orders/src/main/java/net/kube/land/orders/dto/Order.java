package net.kube.land.orders.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Order implements Serializable {

    public String orderId;
    public String orderDate;
    public String orderProductId;
    public String orderPrice;
    public String customerId;
}
