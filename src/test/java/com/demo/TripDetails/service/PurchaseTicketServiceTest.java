package com.demo.TripDetails.service;

import com.demo.TripDetails.entity.PurchaseTicket;
import com.demo.TripDetails.entity.User;
import com.demo.TripDetails.exception.UserTicketNotFoundException;
import com.demo.TripDetails.repository.PurchaseTicketRepository;
import com.demo.TripDetails.repository.UserRepository;
import com.demo.TripDetails.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PurchaseTicketServiceTest {
    @InjectMocks
    PurchaseTicketService purchaseTicketService;
    @Mock
    PurchaseTicketRepository purchaseTicketRepository;
    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPurchaseTicket(){
        PurchaseTicket mockPurchaseTicket = TestUtil.createPurchaseTicketRequest();
        userRepository.save(mockPurchaseTicket.getUser());
        when(purchaseTicketRepository.save(mockPurchaseTicket)).thenReturn(mockPurchaseTicket);
        PurchaseTicket response = purchaseTicketService.purchaseTicket(mockPurchaseTicket);
        assertEquals(mockPurchaseTicket, response);
        verify(purchaseTicketRepository, times(1)).save(mockPurchaseTicket);
    }

    @Test
    public void testFetchDetailsByTicketId_userTicketFound() throws UserTicketNotFoundException {
        int mockId = 111;
        PurchaseTicket mockPurchaseTicket = TestUtil.createPurchaseTicketRequest();
        when(purchaseTicketRepository.findById(mockId)).thenReturn(Optional.of(mockPurchaseTicket));
        PurchaseTicket response = purchaseTicketService.fetchDetailsByTicketId(mockId);
        assertEquals(mockPurchaseTicket, response);
        verify(purchaseTicketRepository, times(1)).findById(mockId);
    }

    @Test
    public void testFetchDetailsByTicketId_userTicketNotFound() {
        int mockId = 111;
        when(purchaseTicketRepository.findById(mockId)).thenReturn(Optional.empty());
        assertThrows(UserTicketNotFoundException.class, ()->purchaseTicketService.fetchDetailsByTicketId(mockId));
        verify(purchaseTicketRepository, times(1)).findById(mockId);
    }

    @Test
    public void testFetchUsersBySection() {
        String mockSection = "sec";
        List<User> mockUsers = TestUtil.createUsersRequest();
        when(userRepository.findBySection(mockSection)).thenReturn(mockUsers);
        List<User> response = purchaseTicketService.fetchUsersBySection(mockSection);
        assertEquals(mockUsers, response);
        verify(userRepository, times(1)).findBySection(mockSection);
    }

    @Test
    public void testRemoveUserByTicketId_userTicketFound() throws UserTicketNotFoundException {
        int mockId = 111;
        PurchaseTicket mockPurchaseTicket = TestUtil.createPurchaseTicketRequest();
        when(purchaseTicketRepository.findById(mockId)).thenReturn(Optional.of(mockPurchaseTicket));
        userRepository.deleteById(mockPurchaseTicket.getUser().getId());
        purchaseTicketRepository.deleteById(mockPurchaseTicket.getId());
        purchaseTicketService.removeUserByTicketId(mockId);
        verify(purchaseTicketRepository, times(1)).findById(mockId);
    }

    @Test
    public void testRemoveUserByTicketId_userTicketNotFound() {
        int mockId = 111;
        when(purchaseTicketRepository.findById(mockId)).thenReturn(Optional.empty());
        assertThrows(UserTicketNotFoundException.class, ()->purchaseTicketService.removeUserByTicketId(mockId));
        verify(purchaseTicketRepository, times(1)).findById(mockId);
    }

    @Test
    public void testUpdateSeatByTicketId_userTicketFound() throws UserTicketNotFoundException {
        int mockId = 111;
        String mockSection = "sec";
        PurchaseTicket mockPurchaseTicket = TestUtil.createPurchaseTicketRequest();
        User user = mockPurchaseTicket.getUser();
        user.setSection(mockSection);
        mockPurchaseTicket.setUser(user);
        userRepository.save(user);
        purchaseTicketRepository.save(mockPurchaseTicket);
        when(purchaseTicketRepository.findById(mockId)).thenReturn(Optional.of(mockPurchaseTicket));
        PurchaseTicket response = purchaseTicketService.updateSeatByTicketId(mockId,mockSection);
        assertEquals(mockPurchaseTicket, response);
        verify(purchaseTicketRepository, times(1)).findById(mockId);
    }

    @Test
    public void testUpdateSeatByTicketId_userTicketNotFound() {
        int mockId = 111;
        String mockSection = "sec";
        when(purchaseTicketRepository.findById(mockId)).thenReturn(Optional.empty());
        assertThrows(UserTicketNotFoundException.class, ()->purchaseTicketService.updateSeatByTicketId(mockId,mockSection));
        verify(purchaseTicketRepository, times(1)).findById(mockId);
    }


}
