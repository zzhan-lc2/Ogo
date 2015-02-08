package com.ogomonkey.db.dao;

import static com.ogomonkey.db.util.QueryBuilderUtils.buildAddressQuery;
import static com.ogomonkey.db.util.QueryBuilderUtils.buildCommQuery;
import static com.ogomonkey.db.util.QueryBuilderUtils.buildStringLikeAnywhereQuery;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.google.common.base.Preconditions;
import com.ogomonkey.common.dao.CustomerDao;
import com.ogomonkey.common.datatype.Address;
import com.ogomonkey.common.datatype.Communication;
import com.ogomonkey.common.entity.customer.Customer;
import com.ogomonkey.db.support.AbstractHibernateDao;

@SuppressWarnings("unchecked")
public class CustomerHibernateDao extends AbstractHibernateDao<Customer> implements CustomerDao {

    @Override
    public Customer findById(String customerId) {
        Preconditions.checkNotNull(customerId, "customerId cannot be null");
        return this.findById(Customer.class, customerId);
    }

    @Override
    public Customer findByLogin(String uniqLogin) {
        Preconditions.checkNotNull(uniqLogin, "uniqLogin cannot be null");

        Criteria query = this.createCriteria(Customer.class)
            .add(Restrictions.eq("login", uniqLogin));

        return (Customer) query.uniqueResult();
    }

    @Override
    public List<Customer> findByCommunication(Communication comm) {
        Preconditions.checkNotNull(comm, "comm cannot be null");

        Criteria query = getCurrentSession().createCriteria(Customer.class, "customer")
            .createAlias("customer.communications", "comm", JoinType.INNER_JOIN);

        query = buildCommQuery(query, comm, "comm");

        return (List<Customer>) query.list();
    }

    @Override
    public List<Customer> findByAddressName(Address address, String firstName, String lastName) {
        Preconditions.checkNotNull(address, "address cannot be null");

        Criteria query = this.createCriteria(Customer.class);
        query = buildAddressQuery(query, address, "address");
        query = buildStringLikeAnywhereQuery(query, "firstName", firstName);
        query = buildStringLikeAnywhereQuery(query, "lastName", lastName);

        return (List<Customer>) query.list();
    }

}
