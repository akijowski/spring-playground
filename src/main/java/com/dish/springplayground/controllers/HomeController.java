package com.dish.springplayground.controllers;

import com.dish.springplayground.model.Flight;
import com.dish.springplayground.services.FlightService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;

@RestController
public class HomeController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello from Spring!";
    }

    @GetMapping("/math/pi")
    public String calcPi() { return "3.141592653589793"; }

    @GetMapping("/flights/flight")
    public Flight getFlight() {
        Flight flight = new Flight();
        Flight.Person passenger = new Flight.Person();
        Flight.Ticket ticket = new Flight.Ticket();

        passenger.setFirstName("Some name");
        passenger.setLastName("Some other name");

        ticket.setPassenger(passenger);
        ticket.setPrice(200);

        flight.setTickets(asList(ticket));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = dateFormat.parse("2017-04-21 08:34");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        flight.setDeparts(date);

        return flight;

//        LocalDateTime dateTime = LocalDate.of(2017, Month.APRIL, 21).atTime(14, 34);
    }

    @GetMapping("/flights")
    public List<Flight> getFlights() {
        Flight flight1 = new Flight();
        Flight.Ticket ticket1 = new Flight.Ticket();
        Flight.Person passenger1 = new Flight.Person();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        passenger1.setFirstName("Some name");
        passenger1.setLastName("Some other name");

        ticket1.setPassenger(passenger1);
        ticket1.setPrice(200);

        flight1.setTickets(asList(ticket1));

        Date date1 = null;
        try {
            date1 = dateFormat.parse("2017-04-21 08:34");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        flight1.setDeparts(date1);

        Flight flight2 = new Flight();
        Flight.Ticket ticket2 = new Flight.Ticket();
        Flight.Person passenger2 = new Flight.Person();

        passenger2.setFirstName("Some other name");

        ticket2.setPassenger(passenger2);
        ticket2.setPrice(400);

        flight2.setTickets(asList(ticket2));

        Date date2 = null;
        try {
            date2 = dateFormat.parse("2017-04-21 08:34");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        flight2.setDeparts(date2);

        return asList(flight1, flight2);
    }

    @PostMapping("/flights/tickets/total")
    public String getTicketTotal(@RequestBody Flight flight) {

        System.out.println(flight);
        return FlightService.getTicketTotal(flight);
    }
}
