package com.demo.TripDetails.util;

import com.demo.TripDetails.entity.PurchaseTicket;
import com.demo.TripDetails.entity.User;

import java.util.Arrays;
import java.util.List;

public class TestUtil {

    public static PurchaseTicket createPurchaseTicketRequest(){
        User user = createUserRequest();
        return new PurchaseTicket(456, "London", "France", user, 20.0);
    }

    public static User createUserRequest(){
        return new User(123, "John", "Doe", "john@gmail.com", "sectionA");
    }

    public static List<User> createUsersRequest(){
        return Arrays.asList(new User(123, "John", "Doe", "john@gmail.com", "sectionA"),
                new User(321, "Jane", "Doe", "jane@gmail.com", "sectionB"));
    }
}
