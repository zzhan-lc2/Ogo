package com.ogomonkey.common.datatype;

import java.util.Date;

import lombok.Data;

@Data
public class DateRange {
    private Date start;
    private boolean startInclusive = true;
    private Date end;
    private boolean endExclusive = true;

}
