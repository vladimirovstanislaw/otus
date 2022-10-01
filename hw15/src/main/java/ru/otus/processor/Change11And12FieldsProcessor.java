package ru.otus.processor;

import ru.otus.model.Message;

public class Change11And12FieldsProcessor implements Processor {
    @Override
    public Message process(Message message) {
        var field11 = message.getField11();
        var field12 = message.getField12();
        var newFieldValue = String.join(message.getField1(), message.getField2(), message.getField3());
        return message.toBuilder().field11(field12).field12(field11).build();
    }
}
