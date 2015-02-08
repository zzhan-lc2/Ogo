package com.ogomonkey.common.entity.business;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

import com.ogomonkey.common.datatype.FileRepositoryPath;
import com.ogomonkey.common.entity.AuditableEntity;

@Data
@ToString(exclude = "business")
public class BusinessLicense extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Business business;
    private String licenseType;
    private String licenseName;
    private String licenseId;
    private String licenseAgency;
    private Date grantDate;
    private Date expireDate;
    private String applicantName;
    private FileRepositoryPath licenseImagePath;
    private String specialNotes;
}
