package com.gridnine.testing;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        FlightFilter departureFilter = new DepartureBeforeCurrentTimeFilter();
        FlightFilter segmentsFilter = new SegmentsArrivalBeforeDepartureFilter();

        Duration maxGroundTime = Duration.ofHours(2); // Задаем максимальное время на земле
        FlightFilter totalGroundTimeFilter = new TotalGroundTimeFilter();

        // Фильтрация перелётов, где вылет раньше текущего момента времени
        List<Flight> filteredFlightsDeparture = departureFilter.filterFlights(flights);
        System.out.println("Filtered flights after departure time: " + filteredFlightsDeparture);
        // Фильтрация перелётов, где сегменты с датой прибытия раньше даты отправления
        List<Flight> filteredFlightsSegments = segmentsFilter.filterFlights(flights);
        System.out.println("Filtered out segments with arrival date earlier than departure date: " + filteredFlightsSegments);

        // Фильтрация перелётов, где общее время, проведённое на земле превышает установленному времени(2 часа)
        List<Flight> filteredFlightsTotalGroundTime = totalGroundTimeFilter.filterFlights(flights, maxGroundTime);
        System.out.println("Filtered flights with total ground time less than " + maxGroundTime.toHours() + " hours: " + filteredFlightsTotalGroundTime);
    }
}
