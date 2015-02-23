package com.ogomonkey.common.entity;

import java.util.Date;
import java.util.Objects;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.ogomonkey.common.datatype.Address;
import com.ogomonkey.common.datatype.EntityStatus;
import com.ogomonkey.common.datatype.Location;

@Data
@EqualsAndHashCode(callSuper = false)
public class AddressEntity extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    private String id;
    private Address address;
    private String relatedEntityType;
    private String relatedEntityId; // could be customer id, instrument id, business id
    private String addressType;
    private EntityStatus status;
    private Date statusDate;
    private Location location;

    public void setRelatedEntity(String relatedEntityType, String relatedEntityId) {
        this.relatedEntityId = relatedEntityId;
        this.relatedEntityType = relatedEntityType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (!(obj instanceof AddressEntity))
            return false;

        AddressEntity other = (AddressEntity) obj;
        if (this.id != null && other.id != null) {
            return Objects.equals(id, other.id);
        }

        return Objects.equals(address, other.address);
    }
}
