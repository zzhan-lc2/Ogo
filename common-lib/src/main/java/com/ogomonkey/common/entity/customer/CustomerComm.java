package com.ogomonkey.common.entity.customer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ogomonkey.common.entity.CommEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "customer")
public class CustomerComm extends CommEntity {
    private static final long serialVersionUID = 1L;

    private Customer customer;

}
