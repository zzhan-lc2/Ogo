package com.ogomonkey.db.dao;

import static com.ogomonkey.db.util.DateUtil.toDate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import lombok.Data;

import org.testng.collections.Lists;

import com.google.common.collect.Sets;
import com.ogomonkey.common.datatype.Address;
import com.ogomonkey.common.datatype.CommType;
import com.ogomonkey.common.datatype.EntityStatus;
import com.ogomonkey.common.datatype.Location;
import com.ogomonkey.common.datatype.RiskStatus;
import com.ogomonkey.common.entity.AddressEntity;
import com.ogomonkey.common.entity.business.BusinessHour;
import com.ogomonkey.common.entity.business.BusinessType;
import com.ogomonkey.common.entity.customer.Customer;
import com.ogomonkey.common.entity.customer.CustomerComm;
import com.ogomonkey.db.util.AddressBuilder;
import com.ogomonkey.db.util.BusinessHourBuilder;
import com.ogomonkey.db.util.CommBuilder;
import com.ogomonkey.db.util.CustomerBuilder;
import com.ogomonkey.db.util.RestaurantBuilder;
import com.ogomonkey.eatery.entity.Restaurant;
import com.ogomonkey.eatery.entity.Table;
import com.ogomonkey.eatery.entity.TableStatus;

@Data
public class TestDataBuilder {
    static final String DATE_PATTERN = "yyyy-MM-dd";

    List<Customer> customers = Lists.newArrayList();
    Customer customerNoCommInstrumentAddr;
    Customer customerHasEmailInit;
    Customer customerHasPhoneVerified;
    Customer customerHasPhoneAddrVerified;
    Customer customerWithEmailCC;

    List<Restaurant> restaurants = Lists.newArrayList();
    Restaurant restaurantNoBar;
    Restaurant bar;
    Restaurant restaurantHasBar;

    List<AddressEntity> addressEntities = Lists.newArrayList();

    public TestDataBuilder() {
        buildAddresses();
        buildCustomers();
        buildRestaurants();
    }

    void buildAddresses() {
        addressEntities.add(
            addLocationToEntity(
                createAddress("line1-a", "line2-a", "city1", "state1", "zipcode1", "type1", "eType1", "eID1"),
                new Location(31.0, -114.0)));
        addressEntities.add(
            addLocationToEntity(
                createAddress("line1-b", "line2-b", "city1", "state1", "zipcode1", "type2", "eType1", "eID1"),
                new Location(31.0, -114.01)));
        addressEntities.add(
            addLocationToEntity(
                createAddress("line1-c", "line2-c", "city2", "state1", "zipcode2", "type1", "eType2", "eID1"),
                new Location(32.0, -114.01)));
        addressEntities.add(
            createAddress("line1-d", "line2-d", "city2", "state1", "zipcode2", "type1", "eType2", "eID2"));
    }

    void buildCustomers() {
        customerNoCommInstrumentAddr = buildCustomer("Customer", "NoCommInstrumentAddr",
            "customerNoCommInstrumentAddr-login", "customerNoCommInstrumentAddr-password",
            EntityStatus.PENDING_VERIFY, toDate("2015-01-05"));
        customers.add(customerNoCommInstrumentAddr);

        customerHasEmailInit = buildCustomer("Customer", "HasEmailInit", "customerHasEmailInit-login",
            "customerHasEmailInit-password",
            EntityStatus.INIT, toDate("2015-01-15"));
        customerHasEmailInit.addCommunication(buildCustomerComm(CommType.EMAIL, "test@email.com", false, "category1",
            EntityStatus.INIT, "2015-01-15", "work"));
        customers.add(customerHasEmailInit);

        customerHasPhoneVerified = buildCustomer("Customer", "HasPhoneVerified", "customerHasPhoneVerified-login",
            "customerHasPhoneVerified-password",
            EntityStatus.VERIFIED, toDate("2015-01-16"));
        customerHasPhoneVerified.addCommunication(buildCustomerComm(CommType.PHONE, "555-5554444", false, "category2",
            EntityStatus.VERIFIED, "2015-01-16", "home"));
        customers.add(customerHasPhoneVerified);

        customerHasPhoneAddrVerified = buildCustomer("Customer", "HasPhoneAddrVerified",
            "customerHasPhoneAddrVerified-login",
            "customerHasPhoneAddrVerified-password",
            EntityStatus.VERIFIED, toDate("2015-01-14"), addressEntities.get(0).getAddress());
        customerHasPhoneAddrVerified.addCommunication(buildCustomerComm(CommType.PHONE, "555-5554433", false,
            "category1",
            EntityStatus.VERIFIED, "2015-01-14", "other"));
        customers.add(customerHasPhoneAddrVerified);
    }

    void buildRestaurants() {
        RestaurantBuilder builder = new RestaurantBuilder()
            .withName("RestaurantNoBar")
            .withAddress(addressEntities.get(1).getAddress())
            .withCapacity(100L)
            .withCategory("FastFood")
            .withCreation(toDate("2015-01-03"), "test user")
            .withDescription("short description 1", "full description 1")
            .withEIN("TAX-ID-1")
            .withFoodStyle("CHINESE", "SHECHUAN")
            .withMainPhone("800-123-4567")
            .withRiskInfo(RiskStatus.NORMAL, toDate("2015-01-04"))
            .withSignupInfo(EntityStatus.VERIFIED, toDate("2015-01-05"))
            .withBusinessHours(createRegularRestaurantHours())
            .withTables(createRestaurantRegularTables(10));
        this.restaurantNoBar = builder.build(BusinessType.RESTAURANT);
        this.restaurants.add(restaurantNoBar);

        builder.withName("Test Bar")
            .withEIN("TAX-ID-2")
            .withCategory("Regular")
            .withFoodStyle("Snack", "Misc")
            .withMainPhone("888-123-4568")
            .withCapacity(30L)
            .withAddress(addressEntities.get(2).getAddress())
            .withCreation(toDate("2015-01-06"), "test user")
            .withRiskInfo(RiskStatus.NORMAL, toDate("2015-01-10"))
            .withSignupInfo(EntityStatus.VERIFIED, toDate("2015-01-11"))
            .withBusinessHours(createRegularRestaurantHours())
            .withTables(createRestaurantRegularTables(5));
        this.bar = builder.build(BusinessType.BAR);
        this.restaurants.add(bar);

    }

    CustomerComm buildCustomerComm(CommType commType, String commNumber, boolean encrypted, String category,
        EntityStatus status, String statusDate, String tag) {
        return new CommBuilder()
            .withCommType(commType)
            .withCategory(category)
            .withCommNumber(commNumber, encrypted)
            .withStatus(status)
            .withStatusDate(toDate(statusDate))
            .addTag(tag)
            .buildCustomerComm();
    }

    public static Customer buildCustomer(String firstName, String lastName, String login, String password,
        EntityStatus signupStatus,
        Date signupDate) {
        return buildCustomer(firstName, lastName, login, password, signupStatus, signupDate, null);
    }

    public static Customer buildCustomer(String firstName, String lastName, String login, String password,
        EntityStatus signupStatus,
        Date signupDate, Address address) {
        return new CustomerBuilder()
            .withName(firstName, lastName)
            .withLogin(login)
            .withPassword(password)
            .withSignupInfo(signupStatus, signupDate)
            .withAddress(address)
            .build();
    }

    public static AddressEntity addLocationToEntity(AddressEntity entity, Location location) {
        entity.setLocation(location);
        return entity;
    }

    public static AddressEntity createAddress(String line1, String line2, String city, String state, String zipcode,
        String type,
        String relatedEntityType, String relatedEntityId) {
        return new AddressBuilder()
            .withAddressLine1(line1)
            .withAddressLine2(line2)
            .withCity(city)
            .withState(state)
            .withCountry("country")
            .withZipcode(zipcode)
            .withAddressType(type)
            .withRelatedEntity(relatedEntityType, relatedEntityId)
            .buildEntity();
    }

    public static Set<Table> createRestaurantRegularTables(int numberOfTables) {
        Set<Table> tables = Sets.newHashSet();
        for (int i = 0; i < numberOfTables; i++) {
            Table table = new Table();
            table.setCapacity(4 + i);
            table.setCreationDate(toDate("2015-01-10"));
            table.setName("Table-" + i);
            table.setTableNumber(i + 1);
            table.setReservable(true);
            table.setTableStatus(TableStatus.AVAILABLE);
            table.setStatusDate(toDate("2015-01-10"));
            table.setNotes("test notes");
        }
        return tables;
    }

    public static Set<BusinessHour> createRegularRestaurantHours() {
        Set<BusinessHour> regular = Sets.newLinkedHashSet();
        BusinessHourBuilder builder = new BusinessHourBuilder()
            .withDayOfWeek(BusinessHour.EVERY_DAY_OF_WEEK)
            .withNotes("regular restaurant hours for test")
            .withStartTime(10, 30)
            .withEndTime(22, 0);
        regular.add(builder.build());
        builder.isHoliday(true)
            .withStartTime(9, 0);
        regular.add(builder.build());

        return regular;
    }

}
