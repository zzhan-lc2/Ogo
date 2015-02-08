package com.ogomonkey.common.entity.business;

import java.util.Currency;
import java.util.Objects;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.ogomonkey.common.entity.NamedEntity;
import com.ogomonkey.common.entity.finance.Instrument;

@Data
@EqualsAndHashCode(callSuper = false)
public class Business extends NamedEntity {
    private static final long serialVersionUID = 1L;

    private BusinessType businessType;
    private String businessCategory;
    private String shortDescription;
    private String fullDescription;
    private String ein; // federal Employer Identification Number
    private Long capacity;
    private String mainPhone;
    private Currency defaultCurrency;
    private Set<BusinessLicense> licenses;
    private Set<BusinessHour> businessHours;
    private Set<BusinessPolicy> businessPolicies;
    private Set<BusinessPersonal> businessPersonals;
    private Set<Instrument> financialInstruments;

    public void addFinancialInstrument(Instrument instrument) {
        if (null == financialInstruments) {
            financialInstruments = Sets.newHashSet();
        }
        financialInstruments.add(instrument);
    }

    public void addLicense(BusinessLicense license) {
        Preconditions.checkNotNull(license, "license cannot be null");

        if (null == licenses) {
            licenses = Sets.newHashSet();
        }
        license.setBusiness(this);
        licenses.add(license);
    }

    public void addBusinessHour(BusinessHour hour) {
        Preconditions.checkNotNull(hour, "hour cannot be null");

        if (null == businessHours) {
            businessHours = Sets.newHashSet();
        }
        hour.setBusiness(this);
        businessHours.add(hour);
    }

    public void addPolicy(BusinessPolicy policy) {
        Preconditions.checkNotNull(policy, "policy cannot be null");

        if (null == businessPolicies) {
            businessPolicies = Sets.newHashSet();
        }
        policy.setBusiness(this);
        businessPolicies.add(policy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (!(obj instanceof Business)) {
            return false;
        }

        Business other = (Business) obj;
        if (this.getId() != null && other.getId() != null) {
            return Objects.equals(this.getId(), other.getId());
        }

        return Objects.equals(this.getName(), other.getName()) &&
            Objects.equals(this.getBusinessType(), other.getBusinessType()) &&
            Objects.equals(this.getMainPhone(), other.getMainPhone());
    }
}
