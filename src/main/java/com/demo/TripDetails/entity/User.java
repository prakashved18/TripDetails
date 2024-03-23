package com.demo.TripDetails.entity;

import com.demo.TripDetails.constants.PurchaseTicketConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = PurchaseTicketConstants.USER_DETAILS_TABLE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = PurchaseTicketConstants.FIRST_NAME_ERROR_MESSAGE)
    private String firstName;
    @NotBlank(message = PurchaseTicketConstants.LAST_NAME_ERROR_MESSAGE)
    private String lastName;
    @NotEmpty(message = PurchaseTicketConstants.EMAIL_ERROR_MESSAGE)
    private String emailAddress;
    @NotBlank(message = PurchaseTicketConstants.SECTION_ERROR_MESSAGE)
    private String section;
}
