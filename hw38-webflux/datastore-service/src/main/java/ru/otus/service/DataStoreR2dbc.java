package ru.otus.service;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import ru.otus.domain.Message;
import ru.otus.repository.MessageRepository;

import static java.time.temporal.ChronoUnit.SECONDS;

@Service
public class DataStoreR2dbc implements DataStore {
    private static final Logger log = LoggerFactory.getLogger(DataStoreR2dbc.class);
    private final MessageRepository messageRepository;
    private final Scheduler workerPool;

    public DataStoreR2dbc(Scheduler workerPool, MessageRepository messageRepository) {
        this.workerPool = workerPool;
        this.messageRepository = messageRepository;
    }

    @Override
    public Mono<Message> saveMessage(Message message) {
        log.info("saveMessage:{}", message);
        return messageRepository.save(message);
    }

    @Override
    public Flux<Message> loadMessages(String roomId) {
        if (!roomId.equals("1408")) {
            log.info("loadMessages roomId:{}", roomId);

            return messageRepository.findByRoomId(roomId)
                    .delayElements(Duration.of(3, SECONDS), workerPool);
        } else {
            log.info("loadMessages roomId:{}", roomId);
            log.info("send all messages from all rooms");

            return messageRepository.findAllMessages()
                    .delayElements(Duration.of(3, SECONDS), workerPool);
        }
    }
}
