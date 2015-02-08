package com.ogomonkey.eatery.entity;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

import com.ogomonkey.common.entity.AuditableEntity;

@Data
@ToString(exclude = "restaurant")
public class Table extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Restaurant restaurant;
    private Integer tableNumber;
    private String name;
    private Integer capacity;
    private Boolean reservable;
    private TableStatus tableStatus;
    private Date statusDate;
    private String equipmentType;
    private String equipmentId; // id for the equipment placed on the table
    private String notes;
}
