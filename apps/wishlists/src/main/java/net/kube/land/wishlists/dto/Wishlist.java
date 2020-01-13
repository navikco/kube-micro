package net.kube.land.wishlists.dto;

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
public class Wishlist implements Serializable {

    public String wishlistId;
    public String wishlistProductId;
    public String wishlistPrice;
    public String customerId;
}
