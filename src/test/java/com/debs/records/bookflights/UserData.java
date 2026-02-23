package com.debs.records.bookflights;

public record UserData(
        String firstName,
        String lastName,
        String email,
        String password,
        String street,
        String city,
        String zip,
        String passengersCount,
        String expectedPrice
) {}


/*
 * 
 * ✔ Immutable
✔ Thread-safe
✔ Perfect for test data
 * 
*/
