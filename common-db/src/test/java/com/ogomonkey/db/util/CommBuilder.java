package com.ogomonkey.db.util;

import java.util.Date;
import java.util.Set;

import com.google.common.collect.Sets;
import com.ogomonkey.common.datatype.CommType;
import com.ogomonkey.common.datatype.Communication;
import com.ogomonkey.common.datatype.EntityStatus;
import com.ogomonkey.common.entity.CommEntity;
import com.ogomonkey.common.entity.customer.CustomerComm;

public final class CommBuilder {
    CommType commType;
    String category;
    String commNumber;
    boolean encrypted; // apply for the field "commNumber"
    Set<String> tags = Sets.newHashSet();
    EntityStatus status;
    Date statusDate;
    String notes;
    String createdBy = "tester";
    Date creationDate = new Date();

    public CommBuilder withCommType(CommType commType) {
        this.commType = commType;
        return this;
    }

    public CommBuilder withCategory(String category) {
        this.category = category;
        return this;
    }

    public CommBuilder withCommNumber(String commNumber, boolean encrypted) {
        this.commNumber = commNumber;
        this.encrypted = encrypted;
        return this;
    }

    public CommBuilder withStatus(EntityStatus status) {
        this.status = status;
        return this;
    }

    public CommBuilder withStatusDate(Date statusDate) {
        this.statusDate = statusDate;
        return this;
    }

    public CommBuilder withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public CommBuilder addTag(String tag) {
        this.tags.add(tag);
        return this;
    }

    public CommBuilder withCreation(Date creationDate, String createdBy) {
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        return this;
    }

    void populate(CommEntity entity) {
        Communication comm = new Communication();
        comm.setCategory(category);
        comm.setCommNumber(commNumber);
        comm.setCommType(commType);
        comm.setEncrypted(encrypted);
        comm.setNotes(notes);
        comm.setTags(tags);
        comm.setStatus(status);
        comm.setStatusDate(statusDate);

        entity.setCommunication(comm);
        entity.setCreationDate(creationDate);
        entity.setCreatedBy(createdBy);
    }

    public CustomerComm buildCustomerComm() {
        CustomerComm entity = new CustomerComm();
        populate(entity);

        return entity;
    }

    public CommEntity buildCommEntity() {
        CommEntity entity = new CommEntity();
        populate(entity);

        return entity;
    }
}
