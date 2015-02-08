package com.ogomonkey.common.entity.business;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "business")
public class BusinessHour {
    public static final Integer EVERY_DAY_OF_WEEK = -1;

    private Long id;
    private Business business;
    private Integer dayOfWeek;
    private Integer startHour;
    private Integer startMinute;
    private Integer endHour;
    private Integer endMinute;
    private boolean holiday;
    private String specialNotes;
}
