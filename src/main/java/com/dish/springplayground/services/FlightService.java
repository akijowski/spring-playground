package com.dish.springplayground.services;

import com.dish.springplayground.model.Flight;

import java.util.List;

public class FlightService {

    public static String getTicketTotal(Flight flight) {
        int total = 0;
        List<Flight.Ticket> tickets = flight.getTickets();
        for(Flight.Ticket ticket: tickets) {
            total += ticket.getPrice();
        }

//        String totalString = String.valueOf(total);
        return "{\"result\":" + total + "}";
    }
}
