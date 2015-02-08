package com.ogomonkey.db.dao;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.google.common.base.Preconditions;
import com.ogomonkey.common.dao.FinancialInstrumentDao;
import com.ogomonkey.common.entity.finance.Instrument;
import com.ogomonkey.common.entity.finance.InstrumentType;
import com.ogomonkey.db.support.AbstractHibernateDao;

public class FinancialInstrumentHibernateDao extends AbstractHibernateDao<Instrument> implements FinancialInstrumentDao {

    @Override
    public Instrument findById(String instrumentId) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(instrumentId), "instrumentId cannot be empty");

        return this.findById(Instrument.class, instrumentId);
    }

    @Override
    public Instrument findByTypeNameNumber(InstrumentType type, String name, String number) {
        Preconditions.checkNotNull(type, "type cannot be null");
        Preconditions.checkArgument(StringUtils.isNotEmpty(number), "number cannot be empty");

        Criteria query = this.createCriteria(Instrument.class)
            .add(Restrictions.eq("type", type))
            .add(Restrictions.eq("number", number));
        if (StringUtils.isNotEmpty(name)) {
            query.add(Restrictions.eq("name", name));
        }
        return (Instrument) query.uniqueResult();
    }

}
