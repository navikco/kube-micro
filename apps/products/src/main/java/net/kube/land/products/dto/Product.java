package net.kube.land.products.dto;

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
public class Product implements Serializable {

    public String productId;
    public String productName;
    public String productPrice;
    public String productCategory;
    public String productStatus;
    public String productStock;
}
