package net.kube.land.subscriptions.dto;

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
public class Subscription implements Serializable {

    public String subscriptionId;
    public String subscriptionProductId;
    public String subscriptionPrice;
    public String customerId;
}
