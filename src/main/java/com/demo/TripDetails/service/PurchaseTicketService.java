package com.demo.TripDetails.service;

import com.demo.TripDetails.constants.PurchaseTicketConstants;
import com.demo.TripDetails.entity.PurchaseTicket;
import com.demo.TripDetails.entity.User;
import com.demo.TripDetails.exception.UserTicketNotFoundException;
import com.demo.TripDetails.repository.PurchaseTicketRepository;
import com.demo.TripDetails.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseTicketService {

    @Autowired
    PurchaseTicketRepository purchaseTicketRepository;

    @Autowired
    UserRepository userRepository;

    public PurchaseTicket purchaseTicket(PurchaseTicket purchaseTicket) {
        userRepository.save(purchaseTicket.getUser());
        return purchaseTicketRepository.save(purchaseTicket);
    }

    public PurchaseTicket fetchDetailsByTicketId(int ticketId) throws UserTicketNotFoundException {
        Optional<PurchaseTicket> fetchedTicketDetails = purchaseTicketRepository.findById(ticketId);
        if(fetchedTicketDetails.isPresent()){
            return fetchedTicketDetails.get();
        } else {
            throw new UserTicketNotFoundException(PurchaseTicketConstants.USER_TICKET_NOT_FOUND_WITH_ID +ticketId);
        }
    }

    public List<User> fetchUsersBySection(String section) {
        return userRepository.findBySection(section);
    }

    public void removeUserByTicketId(int ticketId) throws UserTicketNotFoundException {
        Optional<PurchaseTicket> fetchedTicketDetails = purchaseTicketRepository.findById(ticketId);
        if(fetchedTicketDetails.isPresent()){
             User user = fetchedTicketDetails.get().getUser();
             userRepository.deleteById(user.getId());
             purchaseTicketRepository.deleteById(fetchedTicketDetails.get().getId());
        } else {
            throw new UserTicketNotFoundException(PurchaseTicketConstants.USER_TICKET_NOT_FOUND_WITH_ID +ticketId);
        }
    }

    public PurchaseTicket updateSeatByTicketId(int ticketId, String updatedSection) throws UserTicketNotFoundException {
        Optional<PurchaseTicket> fetchedTicketDetails = purchaseTicketRepository.findById(ticketId);
        if(fetchedTicketDetails.isPresent()){
            PurchaseTicket modifiedTicket = fetchedTicketDetails.get();
            User user = modifiedTicket.getUser();
            user.setSection(updatedSection);
            modifiedTicket.setUser(user);
            userRepository.save(user);
            return purchaseTicketRepository.save(modifiedTicket);
        } else {
            throw new UserTicketNotFoundException(PurchaseTicketConstants.USER_TICKET_NOT_FOUND_WITH_ID +ticketId);
        }
    }
}
