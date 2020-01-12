package net.kube.land.customers.dto;

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
public class Customer implements Serializable {

    public String customerId;
    public String customerName;
    public String customerPhone;
    public String customerEmail;

//    public Customer(String customerId, String customerName, String customerPhone, String customerEmail) {
//        this.customerId = customerId;
//        this.customerName = customerName;
//        this.customerPhone = customerPhone;
//        this.customerEmail = customerEmail;
//    }
}
