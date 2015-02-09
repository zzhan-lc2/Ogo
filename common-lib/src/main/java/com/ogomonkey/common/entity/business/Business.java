package com.ogomonkey.common.entity.business;

import java.util.Currency;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.google.common.base.Objects;
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

    public void addPersonal(BusinessPersonal personal) {
        Preconditions.checkNotNull(personal, "personal cannot be null");

        if (null == businessPersonals) {
            businessPersonals = Sets.newHashSet();
        }
        personal.setBusiness(this);
        businessPersonals.add(personal);
    }

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
        return Objects.hashCode(this.getId());
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
            return Objects.equal(this.getId(), other.getId());
        }

        return Objects.equal(this.getName(), other.getName()) &&
            Objects.equal(this.getBusinessType(), other.getBusinessType()) &&
            Objects.equal(this.getMainPhone(), other.getMainPhone());
    }
}
