package com.demo.TripDetails.entity;

import com.demo.TripDetails.constants.PurchaseTicketConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = PurchaseTicketConstants.TICKET_DETAILS_TABLE)
public class PurchaseTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = PurchaseTicketConstants.FROM_ERROR_MESSAGE)
    private String from;
    @NotBlank(message = PurchaseTicketConstants.TO_ERROR_MESSAGE)
    private String to;
    @OneToOne
    private User user;
    @NotNull(message = PurchaseTicketConstants.PRICE_ERROR_MESSAGE)
    private double pricePaid;
}
