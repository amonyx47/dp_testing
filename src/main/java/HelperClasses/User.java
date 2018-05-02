package HelperClasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    String title;
    String firstname;
    String lastname;
    String email;
    String password;
    int dayOfBirth;
    String monthOfBirth;
    int yearOfBirth;
    boolean hasNewsletter;
    boolean hasSpecialOffers;
    Address address;

}
