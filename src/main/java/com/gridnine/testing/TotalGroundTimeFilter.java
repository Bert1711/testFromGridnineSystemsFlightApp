package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TotalGroundTimeFilter implements FlightFilter {
    @Override
    public List<Flight> filterFlights(List<Flight> flights, Object... parameters) {
        Duration maxGroundTime = (Duration) parameters[0];

        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    long totalGroundTimeHours = 0;
                    for (int i = 0; i < segments.size() - 1; i++) {
                        LocalDateTime arrival = segments.get(i).getArrivalDate();
                        LocalDateTime nextDeparture = segments.get(i + 1).getDepartureDate();
                        long groundTimeHours = Duration.between(arrival, nextDeparture).toHours();
                        totalGroundTimeHours += groundTimeHours;
                    }

                    return totalGroundTimeHours <= maxGroundTime.toHours();
                })
                .collect(Collectors.toList());
    }
}
/**
 * Duration maxGroundTime = (Duration) parameters[0]:
 * Здесь мы извлекаем значение параметра maxGroundTime,которое передано в метод фильтрации.
 * Предполагается, что первый параметр в массиве parameters будет Duration - максимальное время на земле.
 *
 * flights.stream().filter(flight -> ...):
 * Мы преобразуем список перелётов в поток (Stream) и применяем операцию фильтрации,
 * оставляя только перелёты, которые соответствуют заданным условиям.
 *
 * List<Segment> segments = flight.getSegments();:
 * Для каждого перелёта получаем список его сегментов.
 *
 * for (int i = 0; i < segments.size() - 1; i++) { ... }:
 * Мы проходим по списку сегментов и проверяем общее время на земле между соседними сегментами.
 *
 * LocalDateTime arrival = segments.get(i).getArrivalDate();:
 * Получаем дату прилёта текущего сегмента.
 *
 * LocalDateTime nextDeparture = segments.get(i + 1).getDepartureDate();:
 * Получаем дату вылета следующего сегмента.
 *
 * long groundTimeHours = Duration.between(arrival, nextDeparture).toHours();:
 * Мы вычисляем разницу времени между прилётом и следующим вылетом с помощью метода Duration.between(), а затем преобразуем эту разницу в часы.
 *
 * if (groundTimeHours > maxGroundTime.toHours()) { return false; }:
 * Если общее время на земле между сегментами превышает максимальное заданное время (maxGroundTime),
 * то текущий перелёт не проходит фильтрацию, и метод вернёт false.
 *
 * return true;: Если для всех пар сегментов условие не было нарушено, метод вернёт true,
 * означая, что перелёт проходит фильтрацию.
 *
 * .collect(Collectors.toList()):
 * В конце операции фильтрации, мы преобразуем оставшиеся перелёты в список с помощью операции сбора (collect).
 */

