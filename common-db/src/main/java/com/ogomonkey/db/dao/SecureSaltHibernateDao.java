package com.ogomonkey.db.dao;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.google.common.base.Preconditions;
import com.ogomonkey.common.dao.SecureSaltDao;
import com.ogomonkey.common.metrics.ReportMetrics;
import com.ogomonkey.db.support.AbstractHibernateDao;
import com.ogomonkey.security.entity.SaltInfo;

public class SecureSaltHibernateDao extends AbstractHibernateDao<SaltInfo> implements SecureSaltDao {

    @ReportMetrics
    @Override
    public SaltInfo find(String relatedEntityId, @Nullable String relatedEntityType) {
        Preconditions.checkNotNull(relatedEntityId, "relatedEntityId cannot be null");

        Criteria query = this.createCriteria(SaltInfo.class)
            .add(Restrictions.eq("relatedEntityId", relatedEntityId));
        if (StringUtils.isNotEmpty(relatedEntityType)) {
            query = query.add(Restrictions.eq("relatedEntityType", relatedEntityType));
        }
        return (SaltInfo) query.uniqueResult();
    }

}
