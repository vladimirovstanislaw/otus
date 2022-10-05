package homework;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.otus.listener.homework.HistoryListener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;
import ru.otus.processor.EvenSecondException;
import ru.otus.processor.ThrowingExceptionsProcessor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class HistoryListenerTest {

    @Test
    void listenerTest() {
        //given
        var historyListener = new HistoryListener();

        var id = 100L;
        var data = "33";
        var field13 = new ObjectForMessage();
        var field13Data = new ArrayList<String>();
        field13Data.add(data);
        field13.setData(field13Data);

        var message = new Message.Builder(id)
                .field10("field10")
                .field13(field13)
                .build();

        //when
        historyListener.onUpdated(message);
        message.getField13().setData(new ArrayList<>()); //меняем исходное сообщение
        field13Data.clear(); //меняем исходный список

        //then
        var messageFromHistory = historyListener.findMessageById(id);
        assertThat(messageFromHistory).isPresent();
        assertThat(messageFromHistory.get().getField13().getData()).containsExactly(data);
    }

    @Test
    void throwingExceptionTest() throws EvenSecondException, InterruptedException {  //тест для Change11And12FieldsProcessor
        var throwingExceptionsProcessor = new
                ThrowingExceptionsProcessor(() -> new LocalDateTimeEvenSecond().getDate());//не читаемый комментарий из прошлого pr: не очень понятно, как это работает.
        //LocalDateTime.now() возвращает LocalDateTime. Наш интерфейс DateTimeProvider так же возвращает LocalDateTime. С этим всё ясно.
        //Однако как мы в ThrowingExceptionsProcessor в данном вызове
        // var seconds = dateTimeProvider.getDate().getSecond();
        // получаем возможность подменить настоящий интерфейс объекта LocalDateTime нашим интерфейсом?

        Assertions.assertThrows(EvenSecondException.class, () -> {
            throwingExceptionsProcessor.process(new Message(1, "", "", "", "", "", "", "", "", "", "", "", "", new ObjectForMessage()));
        });


    }
}