package net.kube.land.invoices.dto;

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
public class Invoice implements Serializable {

    public String invoiceId;
    public String invoiceProductId;
    public String invoicePrice;
    public String customerId;
}
