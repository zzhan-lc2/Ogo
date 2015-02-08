package com.ogomonkey.common.entity.customer;

import lombok.Getter;
import lombok.Setter;

import com.ogomonkey.common.entity.finance.Instrument;

@Setter
@Getter
public class CustomerInstrument extends Instrument {
    private static final long serialVersionUID = 1L;

    private Customer customer;
}
