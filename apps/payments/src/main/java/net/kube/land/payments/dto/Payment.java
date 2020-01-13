package net.kube.land.payments.dto;

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
public class Payment implements Serializable {

    public String paymentId;
    public String paymentProductId;
    public String paymentPrice;
    public String customerId;
}
