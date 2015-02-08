package com.ogomonkey.eatery.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.ogomonkey.common.entity.AuditableEntity;

@Setter
@Getter
@ToString(exclude = "item")
public class RestaurantOrderItemOption extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    private Long id;
    private RestaurantOrderItem item;
}
