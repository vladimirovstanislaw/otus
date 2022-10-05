package homework;

import ru.otus.processor.DateTimeProvider;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateTimeEvenSecond implements DateTimeProvider {
    @Override
    public LocalDateTime getDate() {
        return LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.of(5, 6, 2));
    }
}
