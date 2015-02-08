package com.ogomonkey.common.entity.business;

import lombok.Data;

import com.ogomonkey.common.entity.finance.Instrument;

@Data
public class BusinessInstrument extends Instrument {
    private static final long serialVersionUID = 1L;

    private Business business;
}
