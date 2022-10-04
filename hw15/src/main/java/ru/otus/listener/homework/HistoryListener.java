package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {
    private Map<Long, Message> messageMap = new HashMap<>();

    @Override
    public void onUpdated(Message msg) {
        Message clone = msg.clone();
        messageMap.put(clone.getId(), clone);

    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return Optional.of(messageMap.get(id));
    }
}
