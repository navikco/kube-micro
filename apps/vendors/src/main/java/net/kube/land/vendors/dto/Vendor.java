package net.kube.land.vendors.dto;

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
public class Vendor implements Serializable {

    public String vendorId;
    public String vendorProductId;
    public String vendorPrice;
    public String customerId;
}
