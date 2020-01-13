package net.kube.land.dues.dto;

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
public class Due implements Serializable {

    public String dueId;
    public String dueProductId;
    public String duePrice;
    public String customerId;
}
