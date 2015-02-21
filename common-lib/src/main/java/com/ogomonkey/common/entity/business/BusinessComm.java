package com.ogomonkey.common.entity.business;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ogomonkey.common.entity.CommEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "business")
public class BusinessComm extends CommEntity {
    private static final long serialVersionUID = 1L;

    private Business business;
}
