package net.kube.land.referrals.dto;

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
public class Referral implements Serializable {

    public String referralId;
    public String referralProductId;
    public String referralPrice;
    public String customerId;
}
