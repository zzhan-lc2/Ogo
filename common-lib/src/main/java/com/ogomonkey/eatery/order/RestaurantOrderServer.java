package com.ogomonkey.eatery.order;

import lombok.Data;

import com.ogomonkey.common.entity.AuditableEntity;

@Data
public class RestaurantOrderServer extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    private Long id;
    private RestaurantOrder order;
}
