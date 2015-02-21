package com.ogomonkey.activity.manager;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.ogomonkey.common.dao.AddressDao;
import com.ogomonkey.common.dao.SecureSaltDao;
import com.ogomonkey.common.datatype.Address;
import com.ogomonkey.common.datatype.EntityStatus;
import com.ogomonkey.common.datatype.UserAction;
import com.ogomonkey.common.entity.AddressEntity;
import com.ogomonkey.common.entity.customer.Customer;
import com.ogomonkey.security.dao.CrypterDao;
import com.ogomonkey.security.dao.PBEncryptStorage;
import com.ogomonkey.security.entity.SaltInfo;
import com.ogomonkey.util.EntityTypeUtil;

@Slf4j
public class AddressActivityManagerImpl implements AddressActivityManager {
    @Autowired
    AddressDao addressDao;

    @Autowired
    CrypterDao crypterDao;

    @Autowired
    SecureSaltDao saltDao;

    public Address encryptAddressForEntity(final Address address, String entityId, @Nullable String entityType) {
        Preconditions.checkNotNull(address, "address cannot be null");
        Preconditions.checkNotNull(entityId, "entityId cannot be null");

        if (address.isLineEncrypted()) {
            // it's already encrypted, simply just return
            return address;
        }

        SaltInfo saltInfo = saltDao.find(entityId, entityType);

        Address result = new Address();
        result.setCity(address.getCity());
        result.setState(address.getState());
        result.setCountry(address.getCountry());
        result.setZipcode(address.getZipcode());
        result.setAddressLine1(encryptLine(address.getAddressLine1(), saltInfo));
        result.setAddressLine2(encryptLine(address.getAddressLine2(), saltInfo));
        result.setLineEncrypted(true);

        return result;
    }

    public Address decryptAddressForEntity(Address address, String entityId, String entityType) {
        Preconditions.checkNotNull(address, "address cannot be null");
        Preconditions.checkNotNull(entityId, "entityId cannot be null");

        if (!address.isLineEncrypted()) {
            // it's already decrypted, simply just return
            return address;
        }

        SaltInfo saltInfo = saltDao.find(entityId, entityType);

        Address result = new Address();
        result.setCity(address.getCity());
        result.setState(address.getState());
        result.setCountry(address.getCountry());
        result.setZipcode(address.getZipcode());
        result.setAddressLine1(decryptLine(address.getAddressLine1(), saltInfo));
        result.setAddressLine2(decryptLine(address.getAddressLine2(), saltInfo));
        result.setLineEncrypted(false);

        return result;
    }

    static Set<EntityStatus> VALID_STATUSES = Sets.newHashSet(EntityStatus.INIT, EntityStatus.PENDING_VERIFY,
        EntityStatus.VERIFIED);

    public AddressEntity addAddressToCustomer(Customer customer, Address address, String addressType,
        UserAction userAction) {
        Preconditions.checkNotNull(customer, "customer cannot be null");
        Preconditions.checkNotNull(address, "address cannot be null");

        // check if the address is already related to the customer
        List<AddressEntity> existingAddrs = addressDao.findByRelatedEntity(EntityTypeUtil.getEntityType(customer),
            customer.getId(), VALID_STATUSES);
        AddressEntity addr = contains(existingAddrs, address, customer.getId());
        if (addr != null) {
            log.info("The address {} is already associated with the customer {}", address, customer.getId());
            return addr;
        }

        AddressEntity addrEntity = new AddressEntity();
        addrEntity.setAddress(address);
        addrEntity.setAddressType(addressType);
        addrEntity.setRelatedEntity(EntityTypeUtil.getEntityType(customer), customer.getId());
        addrEntity.setCreationDate(new Date());
        addrEntity.setLastUpdatedDate(new Date());
        addrEntity.setCreatedBy(userAction.getUser());
        addrEntity.setLastUpdatedBy(userAction.getUser());
        addrEntity.setStatus(EntityStatus.INIT);
        this.addressDao.save(addrEntity);

        return addrEntity;
    }

    String encryptLine(String line, SaltInfo saltInfo) {
        if (StringUtils.isEmpty(line)) {
            return StringUtils.EMPTY;
        }

        PBEncryptStorage pbes = crypterDao.encrypt(line, saltInfo.getPasswordForAlg(), saltInfo.getSalt());
        return pbes.toString();
    }

    String decryptLine(String line, SaltInfo saltInfo) {
        if (StringUtils.isEmpty(line)) {
            return StringUtils.EMPTY;
        }

        PBEncryptStorage pbes = new PBEncryptStorage(line);
        return crypterDao.decrypt(pbes, saltInfo.getPasswordForAlg(), saltInfo.getSalt());
    }

    AddressEntity contains(List<AddressEntity> addrEntityList, Address address, String relatedEntityId) {
        if (CollectionUtils.isEmpty(addrEntityList) || address == null) {
            return null;
        }

        address = this.decryptAddressForEntity(address, relatedEntityId, null);

        for (AddressEntity addrEntity : addrEntityList) {
            if (Objects.equal(decryptAddressForEntity(addrEntity.getAddress(), relatedEntityId, null), address)) {
                return addrEntity;
            }
        }
        return null;
    }

}
