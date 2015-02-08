package com.ogomonkey.common.entity.customer;

import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.ogomonkey.common.entity.CommEntity;
import com.ogomonkey.common.entity.Personal;
import com.ogomonkey.common.entity.finance.Instrument;

@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends Personal {
    private static final long serialVersionUID = 1L;

    private Set<Instrument> financialInstruments;

    public void addFinancialInstrument(Instrument instrument) {
        if (null == financialInstruments) {
            financialInstruments = Sets.newHashSet();
        }
        financialInstruments.add(instrument);
    }

    @Override
    public void addCommunication(CommEntity communication) {
        Preconditions.checkNotNull(communication, "communication cannot be null");
        super.addCommunication(communication);

        if (communication instanceof CustomerComm) {
            ((CustomerComm) communication).setCustomer(this);
        }
    }
}
