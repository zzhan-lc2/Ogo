package com.ogomonkey.db.util;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Sets;
import com.ogomonkey.common.datatype.Address;
import com.ogomonkey.common.datatype.EntityStatus;
import com.ogomonkey.common.datatype.RiskStatus;
import com.ogomonkey.common.entity.CommEntity;
import com.ogomonkey.common.entity.PersonalType;
import com.ogomonkey.common.entity.customer.Customer;
import com.ogomonkey.common.entity.finance.Instrument;

public final class CustomerBuilder {

    String name;
    EntityStatus signupStatus;
    Date signupDate;
    RiskStatus riskStatus;
    Date riskStatusDate;
    Address address;
    Set<CommEntity> communications = Sets.newHashSet();
    String login;
    String encryptedPassword;
    String firstName;
    String lastName;
    PersonalType personalType = PersonalType.REGISTER;
    Set<Instrument> financialInstruments = Sets.newHashSet();
    String createdBy = "tester";
    Date creationDate = new Date();

    public CustomerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder withName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        if (StringUtils.isEmpty(name)) {
            name = "" + this.firstName + " " + this.lastName;
        }
        return this;
    }

    public CustomerBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public CustomerBuilder withPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
        return this;
    }

    public CustomerBuilder withPersonalType(PersonalType personalType) {
        this.personalType = personalType;
        return this;
    }

    public CustomerBuilder withSignupInfo(EntityStatus signupStatus, Date signupDate) {
        this.signupStatus = signupStatus;
        this.signupDate = signupDate;
        return this;
    }

    public CustomerBuilder withRiskInfo(RiskStatus riskStatus, Date riskDate) {
        this.riskStatus = riskStatus;
        this.riskStatusDate = riskDate;
        return this;
    }

    public CustomerBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    public CustomerBuilder addComm(CommEntity comm) {
        this.communications.add(comm);
        return this;
    }

    public CustomerBuilder addFinancialInstrument(Instrument instrument) {
        this.financialInstruments.add(instrument);
        return this;
    }

    public CustomerBuilder withCreation(Date creationDate, String createdBy) {
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        return this;
    }

    public Customer build() {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setLogin(login);
        customer.setEncryptedPassword(encryptedPassword);
        customer.setPersonalType(personalType);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setSignupStatus(signupStatus);
        customer.setSignupDate(signupDate);
        customer.setRiskStatus(riskStatus);
        customer.setRiskStatusDate(riskStatusDate);
        customer.setAddress(address);
        customer.setCommunications(communications);
        customer.setFinancialInstruments(financialInstruments);

        return customer;
    }
}
