package com.ogomonkey.db.dao;

import java.util.List;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.google.common.base.Preconditions;
import com.jcabi.aspects.LogExceptions;
import com.jcabi.aspects.Loggable;
import com.ogomonkey.common.datatype.Address;
import com.ogomonkey.common.metrics.ReportMetrics;
import com.ogomonkey.db.support.AbstractHibernateDao;
import com.ogomonkey.db.util.QueryBuilderUtils;
import com.ogomonkey.eatery.dao.RestaurantDao;
import com.ogomonkey.eatery.entity.Restaurant;

@SuppressWarnings("unchecked")
public class RestaurantHibernateDao extends AbstractHibernateDao<Restaurant> implements RestaurantDao {

    @Override
    public Restaurant findById(String businessId) {
        return this.findById(Restaurant.class, businessId);
    }

    @Override
    public List<Restaurant> findByNameZipcode(String name, String zipcode) {
        Preconditions.checkNotNull(name, "name cannot be null");
        Preconditions.checkNotNull(zipcode, "zipcode cannot be null");

        Address address = new Address();
        address.setZipcode(zipcode);

        Criteria query = this.createCriteria(Restaurant.class)
            .add(Restrictions.eq("name", name).ignoreCase());
        query = QueryBuilderUtils.buildAddressQuery(query, address, "address");
        return (List<Restaurant>) query.list();
    }

    @Override
    @ReportMetrics
    @Loggable(Loggable.DEBUG)
    @LogExceptions
    public List<Restaurant> findByAddress(Address address) {
        Preconditions.checkNotNull(address, "address cannot be null");

        Criteria query = this.createCriteria(Restaurant.class);
        query = QueryBuilderUtils.buildAddressQuery(query, address, "address");

        return (List<Restaurant>) query.list();
    }

    @Override
    @ReportMetrics
    @Loggable(Loggable.DEBUG)
    @LogExceptions
    public List<Restaurant> findByZipcodeStyle(String zipcode, String foodStyle, @Nullable String foodSubStyle) {
        Preconditions.checkNotNull(foodStyle, "foodStyle cannot be null");
        Preconditions.checkNotNull(zipcode, "zipcode cannot be null");

        Address address = new Address();
        address.setZipcode(zipcode);

        Criteria query = this.createCriteria(Restaurant.class)
            .add(Restrictions.eq("foodStyle", foodStyle).ignoreCase());
        if (StringUtils.isEmpty(foodSubStyle)) {
            query = query.add(Restrictions.eq("foodSubStyle", foodSubStyle));
        }
        query = QueryBuilderUtils.buildAddressQuery(query, address, "address");
        return (List<Restaurant>) query.list();
    }
}
