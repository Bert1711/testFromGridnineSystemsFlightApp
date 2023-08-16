package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightFilterTests {

    private List<Flight> flights;

    @BeforeEach
    void setUp() {
        flights = FlightBuilder.createFlights();
    }

    @Test
    void departureBeforeCurrentTimeFilterTest() {
        int expectedNumberOfFlightsAfterFiltering = 5;
        FlightFilter departureFilter = new DepartureBeforeCurrentTimeFilter();
        List<Flight> filteredFlights = departureFilter.filterFlights(flights);

        assertEquals(expectedNumberOfFlightsAfterFiltering, filteredFlights.size());
    }

    @Test
    void segmentsArrivalBeforeDepartureFilterTest() {
        int expectedNumberOfFlightsAfterFiltering = 5;
        FlightFilter segmentsFilter = new SegmentsArrivalBeforeDepartureFilter();
        List<Flight> filteredFlights = segmentsFilter.filterFlights(flights);

        assertEquals(expectedNumberOfFlightsAfterFiltering, filteredFlights.size());
    }

    @Test
    void totalGroundTimeFilterTest() {
        int expectedNumberOfFlightsAfterFiltering = 5;
        FlightFilter totalGroundTimeFilter = new TotalGroundTimeFilter();
        Duration maxGroundTime = Duration.ofHours(2);

        List<Flight> filteredFlights = totalGroundTimeFilter.filterFlights(flights, maxGroundTime);

        assertEquals(expectedNumberOfFlightsAfterFiltering, filteredFlights.size());
    }
}