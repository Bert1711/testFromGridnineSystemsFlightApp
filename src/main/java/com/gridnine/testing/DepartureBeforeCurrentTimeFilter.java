package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
public class DepartureBeforeCurrentTimeFilter implements FlightFilter {
    @Override
    public List<Flight> filterFlights(List<Flight> flights, Object... parameters) {
        LocalDateTime currentTime = LocalDateTime.now();

        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(currentTime)))
                .collect(Collectors.toList());
    }
}
/**
 * flights.stream():
 * Здесь мы преобразуем список flights в поток (stream) объектов типа Flight.
 * Это позволяет нам применять операции стримов над элементами списка.
 *
 * .filter(...):
 * Этот метод является промежуточной операцией стрима.
 * Он принимает в качестве аргумента предикат (условие) и фильтрует элементы потока,
 * оставляя только те, которые удовлетворяют предикату.
 *
 * flight -> flight.getSegments().stream() ...:
 * Эта лямбда-функция применяется к каждому объекту типа Flight в потоке.
 * Внутри этой функции мы обращаемся к сегментам (segments) каждого перелёта.
 *
 * .allMatch(...):
 * Этот метод также является промежуточной операцией.
 * Он проверяет, удовлетворяют ли все элементы потока заданному предикату.
 * В данном случае, мы хотим убедиться, что все сегменты каждого перелёта имеют дату вылета (departureDate),
 * которая находится после текущего времени (currentTime).
 *
 * segment -> segment.getDepartureDate().isAfter(currentTime):
 * Эта лямбда-функция применяется к каждому объекту типа Segment в потоке сегментов.
 * Она проверяет, что дата вылета (departureDate) сегмента находится после текущего времени (currentTime).
 *
 * .collect(Collectors.toList()):
 * Этот метод завершает операции над стримом и создаёт из его элементов список (List).
 * В данном случае, он создаёт список перелётов, которые прошли фильтрацию по условию
 * (дата вылета для всех сегментов находится после текущего времени).
 *
 * В итоге этот класс фильтрует список перелётов,
 * оставляя только те, у которых все сегменты имеют дату вылета (departureDate),
 * находящуюся после текущего времени (currentTime).
 */




