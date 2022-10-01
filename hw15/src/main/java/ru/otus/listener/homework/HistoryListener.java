package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {
    private MessageStateKeeper messageStateKeeper = new MessageStateKeeper(LocalDateTime::now);//не очень понятно как это работает.

    @Override
    public void onUpdated(Message msg) {
        ObjectForMessage objectForMessage = new ObjectForMessage();
        List<String> listOfData = new ArrayList<>();
        for (var data : msg.getField13().getData()) {
            listOfData.add(data);
        }
        objectForMessage.setData(listOfData);
        Message messageHistoricalCopy = new Message(msg.getId(), msg.getField1(), msg.getField2(), msg.getField3(), msg.getField4(), msg.getField5(), msg.getField6(), msg.getField7(), msg.getField8(), msg.getField9(), msg.getField10(), msg.getField11(), msg.getField12(), objectForMessage);
        messageStateKeeper.saveMessageState(messageHistoricalCopy);

    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return Optional.of(messageStateKeeper.getLastMessageState(id));
    }
}
