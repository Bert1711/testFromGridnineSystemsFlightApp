package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

public class SegmentsArrivalBeforeDepartureFilter implements FlightFilter {
    @Override
    public List<Flight> filterFlights(List<Flight> flights, Object... parameters) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }
}
/**
 *
 * flights.stream():
 * Здесь мы преобразуем список flights в поток (stream) объектов типа Flight.
 * Это позволяет нам применять операции стримов над элементами списка.
 *
 * .filter(...):
 * Этот метод является промежуточной операцией стрима.
 * Он принимает в качестве аргумента предикат (условие) и фильтрует элементы потока, оставляя только те,
 * которые удовлетворяют предикату.
 *
 * flight -> flight.getSegments().stream() ...:
 * Эта лямбда-функция применяется к каждому объекту типа Flight в потоке.
 * Внутри этой функции мы обращаемся к сегментам (segments) каждого перелёта.
 *
 * .allMatch(...):
 * Этот метод также является промежуточной операцией.
 * Он проверяет, удовлетворяют ли все элементы потока заданному предикату.
 * В данном случае, мы хотим убедиться, что для каждого сегмента перелёта дата прилёта (arrivalDate)
 * находится до даты вылета (departureDate).
 *
 * segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()):
 * Эта лямбда-функция применяется к каждому объекту типа Segment в потоке сегментов.
 * Она проверяет, что дата прилёта (arrivalDate) сегмента находится до даты вылета (departureDate).
 *
 * .collect(Collectors.toList()):
 * Этот метод завершает операции над стримом и создаёт из его элементов список (List).
 * В данном случае, он создаёт список перелётов, у которых все сегменты соответствуют условию
 * (дата прилёта находится перед датой вылета).
 *
 * В итоге этот класс фильтрует список перелётов, оставляя только те,
 * у которых все сегменты имеют правильную последовательность временных интервалов (дата прилёта перед датой вылета).
 *
 */

