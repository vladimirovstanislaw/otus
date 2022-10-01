package ru.otus.listener.homework;

import ru.otus.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageStateKeeper {
    //хранилище наших исторических данных по сообщениям
    private final List<MessageMemento> listOfHistoricalMessages = new ArrayList<>();
    private final DateTimeProvider dateTimeProvider;

    public MessageStateKeeper(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    public void saveMessageState(Message message) {
        listOfHistoricalMessages.add(new MessageMemento(new MessageState(message), dateTimeProvider.getDate()));
    }

    public Message getLastMessageState(long id) {
        Message message = null;
        if (listOfHistoricalMessages.size() == 0) {
            return message;
        }
        for (int i = 0; i < listOfHistoricalMessages.size(); i++) {
            if (listOfHistoricalMessages.get(i).state().message().getId() == id) {
                return listOfHistoricalMessages.get(i).state().message();
            }
        }
        return message;
    }
}
