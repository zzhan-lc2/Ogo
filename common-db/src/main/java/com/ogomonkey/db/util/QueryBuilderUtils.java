package com.ogomonkey.db.util;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.ogomonkey.common.datatype.Address;
import com.ogomonkey.common.datatype.Communication;

public final class QueryBuilderUtils {

    public static Criteria buildStringLikeAnywhereQuery(Criteria query, String fieldName, String likeValue) {
        if (StringUtils.isNotEmpty(likeValue)) {
            query = query.add(Restrictions.like(fieldName, likeValue, MatchMode.ANYWHERE).ignoreCase());
        }
        return query;
    }

    public static Criteria buildCommQuery(Criteria query, Communication comm, String owningPropertyName) {
        if (comm == null) {
            return query;
        }

        if (comm.getCommType() != null) {
            query = query.add(Restrictions.eq(appendFieldName(owningPropertyName, "communication.commType"),
                comm.getCommType()));
        }
        if (StringUtils.isNotEmpty(comm.getCommNumber())) {
            query = query.add(Restrictions.eq(appendFieldName(owningPropertyName, "communication.commNumber"),
                comm.getCommNumber()).ignoreCase());
        }
        if (StringUtils.isNotEmpty(comm.getCategory())) {
            query = query.add(Restrictions.eq(appendFieldName(owningPropertyName, "communication.category"),
                comm.getCategory()).ignoreCase());
        }

        return query;
    }

    public static Criteria buildAddressQuery(Criteria query, Address address, String owningPropertyName) {
        if (address == null) {
            return query;
        }

        if (StringUtils.isNotEmpty(address.getZipcode())) {
            query = query.add(Restrictions.eq(appendFieldName(owningPropertyName, "zipcode"), address.getZipcode())
                .ignoreCase());
        }
        if (StringUtils.isNotEmpty(address.getCountry())) {
            query = query.add(Restrictions.eq(appendFieldName(owningPropertyName, "country"), address.getCountry())
                .ignoreCase());
        }
        if (StringUtils.isNotEmpty(address.getState())) {
            query = query.add(Restrictions.eq(appendFieldName(owningPropertyName, "state"), address.getState())
                .ignoreCase());
        }
        if (StringUtils.isNotEmpty(address.getCity())) {
            query = query.add(Restrictions.eq(appendFieldName(owningPropertyName, "city"), address.getCity())
                .ignoreCase());
        }
        if (StringUtils.isNotEmpty(address.getAddressLine1())) {
            query = query.add(Restrictions.eq(appendFieldName(owningPropertyName, "addressLine1"),
                address.getAddressLine1()).ignoreCase());
        }
        if (StringUtils.isNotEmpty(address.getAddressLine2())) {
            query = query.add(Restrictions.eq(appendFieldName(owningPropertyName, "addressLine2"),
                address.getAddressLine2()).ignoreCase());
        }

        return query;
    }

    static String appendFieldName(String owningPropertyName, String fieldName) {
        if (StringUtils.isEmpty(owningPropertyName)) {
            return fieldName;
        }
        return new StringBuilder()
            .append(owningPropertyName)
            .append(".").append(fieldName)
            .toString();
    }
}
