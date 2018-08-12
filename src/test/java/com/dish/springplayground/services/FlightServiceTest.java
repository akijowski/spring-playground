package com.dish.springplayground.services;

import com.dish.springplayground.model.Flight;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
public class FlightServiceTest {

    private Flight flight1, flight2;

    @Before
    public void setup() {
        flight1 = new Flight();

        Flight.Ticket ticket1 = new Flight.Ticket();
        ticket1.setPrice(100);
        flight1.setTickets(asList(ticket1));

        flight2 = new Flight();
        Flight.Ticket ticket2 = new Flight.Ticket();
        ticket2.setPrice(75);
        Flight.Ticket ticket3 = new Flight.Ticket();
        ticket3.setPrice(50);
        flight2.setTickets(asList(ticket2, ticket3));
    }

    @Test
    public void getTicketTotal_returns_total_for_one_flight() {
        String expected = "{\"result\":" + 100 + "}";

        String actual = FlightService.getTicketTotal(flight1);

        assertThat(actual, is(expected));
    }

    @Test
    public void getTicketTotal_returns_total_for_two_tickets() {
        String expected = "{\"result\":" + 125 + "}";

        String actual = FlightService.getTicketTotal(flight2);

        assertThat(actual, is(expected));
    }
}