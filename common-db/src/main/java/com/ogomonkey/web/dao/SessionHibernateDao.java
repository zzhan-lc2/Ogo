package com.ogomonkey.web.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.google.common.base.Preconditions;
import com.ogomonkey.common.datatype.DateRange;
import com.ogomonkey.db.support.AbstractHibernateDao;
import com.ogomonkey.db.util.QueryBuilderUtils;
import com.ogomonkey.web.entity.Session;

@SuppressWarnings("unchecked")
public class SessionHibernateDao extends AbstractHibernateDao<Session> implements SessionDao {

    @Override
    public Session findById(String sessionId) {
        return this.findById(Session.class, sessionId);
    }

    @Override
    public List<Session> findByUser(String userId, DateRange lastAccessDateRange) {
        Preconditions.checkNotNull(userId, "userId cannot be null");

        Criteria query = this.createCriteria(Session.class)
            .add(Restrictions.eq("userId", userId));
        if (lastAccessDateRange != null) {
            query = QueryBuilderUtils.buildDateRageQuery(query, lastAccessDateRange, "lastAccessDate");
        }

        return (List<Session>) query.list();
    }

    @Override
    public List<Session> findByIpAgent(String ip, String userAgent, DateRange lastAccessDateRange) {
        Preconditions.checkNotNull(ip, "ip cannot be null");

        Criteria query = this.createCriteria(Session.class)
            .add(Restrictions.eq("ip", ip));
        if (StringUtils.isNotEmpty(userAgent)) {
            query = query.add(Restrictions.eq("userAgent", userAgent));
        }
        if (lastAccessDateRange != null) {
            query = QueryBuilderUtils.buildDateRageQuery(query, lastAccessDateRange, "lastAccessDate");
        }

        return (List<Session>) query.list();
    }

}
