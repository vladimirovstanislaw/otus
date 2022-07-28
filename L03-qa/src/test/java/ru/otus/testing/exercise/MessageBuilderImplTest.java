package ru.otus.testing.exercise;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static ru.otus.testing.exercise.DefaultMessageTemplateProvider.DEFAULT_TEMPLATE;

class MessageBuilderImplTest {
    public static final String DEFAULT_TEMPLATE_NAME = "defaultTemplate";
    public static final String DEFAULT_MESSAGE_TEXT = "defaultText";
    public static final String DEFAULT_SIGNATURE = "defaultSignature";

    private MessageBuilderImpl messageBuilder;

    private MessageTemplateProvider templateProvider;

    @BeforeEach
    void setUp() {
        templateProvider = Mockito.mock(MessageTemplateProvider.class);
        messageBuilder = new MessageBuilderImpl(templateProvider);
    }

    @Test
    void buildMessage() throws IllegalAccessException {
        Mockito.when(templateProvider.getMessageTemplate(any())).thenReturn(DEFAULT_TEMPLATE);
        String expectedMessage = String.format(DEFAULT_TEMPLATE, DEFAULT_MESSAGE_TEXT, DEFAULT_SIGNATURE);
        var actualMessage = messageBuilder.buildMessage(DEFAULT_TEMPLATE_NAME, DEFAULT_MESSAGE_TEXT, DEFAULT_SIGNATURE);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void buildMessage2() throws IllegalAccessException {
        Mockito.when(templateProvider.getMessageTemplate(any())).thenReturn(DEFAULT_TEMPLATE);

        messageBuilder.buildMessage(DEFAULT_TEMPLATE_NAME, DEFAULT_MESSAGE_TEXT, DEFAULT_SIGNATURE);
        Mockito.verify(templateProvider, Mockito.times(1)).getMessageTemplate(DEFAULT_TEMPLATE_NAME);
    }

    @DisplayName("Should throw TemplateNotFoundException when templateName is null.")
    @Test
    void buildMessage3() {
        //Mockito.when(templateProvider.getMessageTemplate(any())).thenReturn(DEFAULT_TEMPLATE);//To break test. Because every test running in its own object
        Assertions.assertThrows(TemplateNotFoundException.class, () -> messageBuilder.buildMessage(DEFAULT_TEMPLATE_NAME, DEFAULT_MESSAGE_TEXT, DEFAULT_SIGNATURE));
    }

    @AfterEach
    void tearDown() {
    }

}