package com.demo.TripDetails.controller;

import com.demo.TripDetails.entity.PurchaseTicket;
import com.demo.TripDetails.entity.User;
import com.demo.TripDetails.exception.UserTicketNotFoundException;
import com.demo.TripDetails.service.PurchaseTicketService;
import com.demo.TripDetails.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PurchaseTicketControllerTest {

    @InjectMocks
    PurchaseTicketController purchaseTicketController;
    @Mock
    PurchaseTicketService purchaseTicketService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPurchaseTicket(){
        PurchaseTicket mockPurchaseTicket = TestUtil.createPurchaseTicketRequest();
        when(purchaseTicketService.purchaseTicket(mockPurchaseTicket)).thenReturn(mockPurchaseTicket);
        ResponseEntity<PurchaseTicket> response = purchaseTicketController.purchaseTicket(mockPurchaseTicket);
        assertEquals(mockPurchaseTicket, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(purchaseTicketService, times(1)).purchaseTicket(mockPurchaseTicket);
    }

    @Test
    public void testFetchDetailsByTicketId() throws UserTicketNotFoundException {
        int mockId = 111;
        PurchaseTicket mockPurchaseTicket = TestUtil.createPurchaseTicketRequest();
        when(purchaseTicketService.fetchDetailsByTicketId(mockId)).thenReturn(mockPurchaseTicket);
        ResponseEntity<PurchaseTicket> response = purchaseTicketController.fetchDetailsByTicketId(mockId);
        assertEquals(mockPurchaseTicket, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(purchaseTicketService, times(1)).fetchDetailsByTicketId(mockId);
    }

    @Test
    public void testFetchUsersBySection()  {
        String mockSection = "sectionA";
        List<User> mockUsers = TestUtil.createUsersRequest();
        when(purchaseTicketService.fetchUsersBySection(mockSection)).thenReturn(mockUsers);
        ResponseEntity<List<User>> response = purchaseTicketController.fetchUsersBySection(mockSection);
        assertEquals(mockUsers, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(purchaseTicketService, times(1)).fetchUsersBySection(mockSection);
    }

    @Test
    public void testRemoveUserByTicketId() throws UserTicketNotFoundException {
        int mockId = 111;
        String mockString = "User with ticketId:"+mockId +" is removed!";
        purchaseTicketService.removeUserByTicketId(mockId);
        ResponseEntity<String> response = purchaseTicketController.removeUserByTicketId(mockId);
        assertEquals(mockString, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(purchaseTicketService, times(2)).removeUserByTicketId(mockId);
    }

    @Test
    public void testUpdateSeatByTicketId() throws UserTicketNotFoundException {
        int mockId = 111;
        String mockSection = "sec";
        PurchaseTicket mockPurchaseTicket = TestUtil.createPurchaseTicketRequest();
        when(purchaseTicketService.updateSeatByTicketId(mockId,mockSection)).thenReturn(mockPurchaseTicket);
        ResponseEntity<PurchaseTicket> response = purchaseTicketController.updateSeatByTicketId(mockId,mockSection);
        assertEquals(mockPurchaseTicket, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(purchaseTicketService, times(1)).updateSeatByTicketId(mockId,mockSection);
    }
}
