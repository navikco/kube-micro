package net.kube.land.accounts.dto;

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
public class Account implements Serializable {

    public String accountId;
    public String accountName;
    public String accountStatus;
    public String accountType;
}
