package com.ogomonkey.common.entity.business;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.joda.money.Money;

import com.ogomonkey.common.datatype.FileRepositoryPath;
import com.ogomonkey.common.datatype.Sex;
import com.ogomonkey.common.entity.Personal;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "business")
public class BusinessPersonal extends Personal {
    private String id;
    private Business business;
    private String userRole; // like: business Admin; FinancialAdmin; CustomerService; etc.
    private String positionType;
    private Sex sex;
    private String employeeNumber;
    private SalaryType salaryType;
    private Money salary;
    private Date hireDate;
    private Date terminationDate;
    private String hiringAgency;
    private FileRepositoryPath badgePhotoUrl;
    private String notes;
}
