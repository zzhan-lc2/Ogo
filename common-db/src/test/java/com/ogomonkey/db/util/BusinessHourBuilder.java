package com.ogomonkey.db.util;

import com.ogomonkey.common.entity.business.BusinessHour;

public class BusinessHourBuilder {
    private Integer dayOfWeek;
    private Integer startHour;
    private Integer startMinute;
    private Integer endHour;
    private Integer endMinute;
    private boolean holiday;
    private String specialNotes;

    public BusinessHourBuilder withDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public BusinessHourBuilder withNotes(String specialNotes) {
        this.specialNotes = specialNotes;
        return this;
    }

    public BusinessHourBuilder withStartTime(Integer startHour, Integer startMinute) {
        this.startHour = startHour;
        this.startMinute = startMinute;
        return this;
    }

    public BusinessHourBuilder withEndTime(Integer endHour, Integer endMinute) {
        this.endHour = endHour;
        this.endMinute = endMinute;
        return this;
    }

    public BusinessHourBuilder isHoliday(boolean holiday) {
        this.holiday = holiday;
        return this;
    }

    public BusinessHour build() {
        BusinessHour hour = new BusinessHour();
        hour.setDayOfWeek(dayOfWeek);
        hour.setStartHour(startHour);
        hour.setStartMinute(startMinute);
        hour.setEndHour(endHour);
        hour.setEndMinute(endMinute);
        hour.setHoliday(holiday);
        hour.setSpecialNotes(specialNotes);
        return hour;
    }
}
