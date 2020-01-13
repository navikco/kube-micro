package net.kube.land.categories.dto;

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
public class Category implements Serializable {

    public String categoryId;
    public String categoryProductId;
    public String categoryPrice;
    public String customerId;
}
