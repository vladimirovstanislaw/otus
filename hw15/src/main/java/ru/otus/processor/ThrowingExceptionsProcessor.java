package ru.otus.processor;

import ru.otus.model.Message;

import java.time.LocalDateTime;

public class ThrowingExceptionsProcessor implements Processor {
    private final DateTimeProvider dateTimeProvider;

    public ThrowingExceptionsProcessor(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) throws EvenSecondException {
        var seconds = dateTimeProvider.getDate().getSecond();
        if (seconds % 2 == 0) {
            throw new EvenSecondException();
        }
        return null;
    }
}

