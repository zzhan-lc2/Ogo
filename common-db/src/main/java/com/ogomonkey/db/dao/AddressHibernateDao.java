package com.ogomonkey.db.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.google.common.base.Preconditions;
import com.jcabi.aspects.LogExceptions;
import com.jcabi.aspects.Loggable;
import com.ogomonkey.common.dao.AddressDao;
import com.ogomonkey.common.entity.AddressEntity;
import com.ogomonkey.common.metrics.ReportMetrics;
import com.ogomonkey.db.support.AbstractHibernateDao;

@SuppressWarnings("unchecked")
public class AddressHibernateDao extends AbstractHibernateDao<AddressEntity> implements AddressDao {

    @Override
    public AddressEntity findById(String addressId) {
        return this.findById(AddressEntity.class, addressId);
    }

    @ReportMetrics
    @Override
    @LogExceptions
    @Loggable(Loggable.INFO)
    public List<AddressEntity> findByRelatedEntity(String relatedEntityType, String relatedEntityId) {
        Preconditions.checkNotNull(relatedEntityType, "relatedEntityType cannot be null");
        Preconditions.checkNotNull(relatedEntityId, "relatedEntityId cannot be null");

        Criteria query = this.createCriteria(AddressEntity.class)
            .add(Restrictions.eq("relatedEntityType", relatedEntityType))
            .add(Restrictions.eq("relatedEntityId", relatedEntityId));
        return (List<AddressEntity>) query.list();
    }

    @ReportMetrics
    @Override
    @LogExceptions
    @Loggable(Loggable.INFO)
    public List<AddressEntity> findByLatLonRange(double latDD, double lonDD, double delta, int maxReturns) {
        Criteria query = this.createCriteria(AddressEntity.class)
            .add(Restrictions.le("location.latDD", latDD + delta))
            .add(Restrictions.ge("location.latDD", latDD - delta))
            .add(Restrictions.le("location.lonDD", lonDD + delta))
            .add(Restrictions.ge("location.lonDD", lonDD - delta));
        if (maxReturns > 0) {
            query.setMaxResults(maxReturns);
        }

        return (List<AddressEntity>) query.list();
    }

}
