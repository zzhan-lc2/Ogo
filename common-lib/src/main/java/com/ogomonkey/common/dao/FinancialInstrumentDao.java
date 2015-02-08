package com.ogomonkey.common.dao;

import com.ogomonkey.common.entity.finance.Instrument;
import com.ogomonkey.common.entity.finance.InstrumentType;

public interface FinancialInstrumentDao {
    void save(Instrument instrument);

    Instrument findById(String instrumentId);

    Instrument findByTypeNameNumber(InstrumentType type, String name, String number);
}
