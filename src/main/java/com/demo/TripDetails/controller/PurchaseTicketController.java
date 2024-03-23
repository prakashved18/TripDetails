package com.demo.TripDetails.controller;

import com.demo.TripDetails.entity.PurchaseTicket;
import com.demo.TripDetails.entity.User;
import com.demo.TripDetails.exception.UserTicketNotFoundException;
import com.demo.TripDetails.service.PurchaseTicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PurchaseTicketController {

    @Autowired
    PurchaseTicketService purchaseTicketService;

    @PostMapping("/purchaseTicket")
    public ResponseEntity<PurchaseTicket> purchaseTicket(@Valid @RequestBody PurchaseTicket purchaseTicket){
        PurchaseTicket purchasedTicketDetails = purchaseTicketService.purchaseTicket(purchaseTicket);
        return new ResponseEntity<>(purchasedTicketDetails, HttpStatus.CREATED);
    }

    @GetMapping("/fetchDetailsByTicketId/{ticketId}")
    public ResponseEntity<PurchaseTicket> fetchDetailsByTicketId(@PathVariable int ticketId) throws UserTicketNotFoundException {
        PurchaseTicket fetchedTicketDetails = purchaseTicketService.fetchDetailsByTicketId(ticketId);
        return new ResponseEntity<>(fetchedTicketDetails, HttpStatus.OK);
    }

    @GetMapping("/fetchUsersBySection/{section}")
    public ResponseEntity<List<User>> fetchUsersBySection(@PathVariable String section){
        List<User> userSeatDetails = purchaseTicketService.fetchUsersBySection(section);
        return new ResponseEntity<>(userSeatDetails, HttpStatus.OK);
    }

    @DeleteMapping("/removeUserByTicketId/{ticketId}")
    public ResponseEntity<String> removeUserByTicketId(@PathVariable int ticketId) throws UserTicketNotFoundException {
        purchaseTicketService.removeUserByTicketId(ticketId);
        return new ResponseEntity<>("User with ticketId:"+ticketId +" is removed!", HttpStatus.OK);
    }

    @PatchMapping("/updateSeatByTicketId/{ticketId}")
    public ResponseEntity<PurchaseTicket> updateSeatByTicketId(@PathVariable int ticketId, @RequestParam String updatedSection) throws UserTicketNotFoundException {
        PurchaseTicket updatedTicketDetails = purchaseTicketService.updateSeatByTicketId(ticketId, updatedSection);
        return new ResponseEntity<>(updatedTicketDetails, HttpStatus.OK);
    }
}
