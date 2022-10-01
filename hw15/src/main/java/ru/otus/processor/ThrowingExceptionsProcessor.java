package ru.otus.processor;

import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;

import java.time.LocalDateTime;

public class ThrowingExceptionsProcessor implements Processor {
    @Override
    public Message process(Message message) throws EvenSecondException {
        var seconds = LocalDateTime.now().getSecond();
        if (seconds % 2 == 0) {
            throw new EvenSecondException();
        }
        return null;
    }
}

