package HelperClasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    String firstname;
    String lastname;
    String company;
    String address;
    String address2;
    String city;
    String state;
    String postalCode;
    String country;
    String additional;
    String homePhone;
    String mobilePhone;
    String addressAlias;

}
