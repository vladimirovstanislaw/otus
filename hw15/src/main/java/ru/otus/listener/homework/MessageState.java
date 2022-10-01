package ru.otus.listener.homework;

import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;

import java.util.ArrayList;
import java.util.List;

public record MessageState(Message message) {
    public MessageState(Message message) {

        ObjectForMessage objectForMessage = new ObjectForMessage();
        List<String> listOfData = new ArrayList<>();
        for (var data : message.getField13().getData()) {
            listOfData.add(data);
        }
        objectForMessage.setData(listOfData);

        this.message = new Message(message.getId(), message.getField1(), message.getField2(), message.getField3(), message.getField4(), message.getField5(), message.getField6(), message.getField7(), message.getField8(), message.getField9(), message.getField10(), message.getField11(), message.getField12(), objectForMessage);
    }
}
