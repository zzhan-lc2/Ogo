package com.ogomonkey.eatery.order;

import java.util.Currency;
import java.util.Objects;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.DateTime;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.ogomonkey.common.entity.AuditableEntity;

@Setter
@Getter
@ToString
public class RestaurantOrder extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    private String id;
    private String restaurantId;
    private String mainCustomerId;
    private Integer tableNumber;
    private DateTime orderDateTime; // the date & time that this order is getting created
    private Integer numberOfCustomers;
    private OrderStatus orderStatus;
    private DateTime orderStatusTime;
    private Currency currency;
    private Double orderTotal;
    private Double taxAmount;
    private Double tipsAmount;
    private Double totalAmount;
    private ReceiptType receiptType;
    private Set<RestaurantOrderItem> orderItems;
    private Set<RestaurantOrderServer> servers;
    private Set<RestaurantOrderEvent> events;

    public RestaurantOrder() {
        this.orderDateTime = new DateTime();
    }

    public void addOrderItem(RestaurantOrderItem item) {
        Preconditions.checkNotNull(item, "item cannot be null");

        if (orderItems == null) {
            orderItems = Sets.newHashSet();
        }
        item.setOrder(this);
        orderItems.add(item);
    }

    public void addEvent(RestaurantOrderEvent event) {
        Preconditions.checkNotNull(event, "event cannot be null");

        if (events == null) {
            events = Sets.newLinkedHashSet();
        }
        event.setOrder(this);
        events.add(event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (!(obj instanceof RestaurantOrder)) {
            return false;
        }

        RestaurantOrder other = (RestaurantOrder) obj;
        if (id != null && other.id != null) {
            return Objects.equals(id, other.id);
        }

        return Objects.equals(restaurantId, other.restaurantId) &&
            Objects.equals(this.tableNumber, other.tableNumber) &&
            Objects.equals(this.orderDateTime, other.orderDateTime);
    }
}
