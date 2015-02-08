package com.ogomonkey.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.ogomonkey.common.datatype.Communication;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommEntity extends AuditableEntity {
    private String id;
    private Communication communication;
}
