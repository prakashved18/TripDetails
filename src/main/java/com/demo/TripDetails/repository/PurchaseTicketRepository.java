package com.demo.TripDetails.repository;

import com.demo.TripDetails.entity.PurchaseTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseTicketRepository extends JpaRepository<PurchaseTicket, Integer> {
}
