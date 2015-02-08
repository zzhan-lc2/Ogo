package com.ogomonkey.common.datatype;

import lombok.Data;

@Data
public abstract class AbstractRange<T> {
    private T start;
    private boolean startInclusive = true;
    private T end;
    private boolean endExclusive = true;
}
