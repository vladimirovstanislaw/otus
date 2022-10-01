package ru.otus.listener.homework;

import java.time.LocalDateTime;

record MessageMemento(MessageState state, LocalDateTime createdAt) {
    public MessageMemento(MessageState state, LocalDateTime createdAt) {
        this.state = state;
        this.createdAt = createdAt;
    }
}
