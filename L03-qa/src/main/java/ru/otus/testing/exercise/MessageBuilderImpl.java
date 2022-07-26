package ru.otus.testing.exercise;

public class MessageBuilderImpl implements MessageBuilder {
    private final MessageTemplateProvider templateProvider;

    public MessageBuilderImpl(MessageTemplateProvider messageTemplateProvider) {
        this.templateProvider = messageTemplateProvider;
    }

    @Override
    public String buildMessage(String templateName, String text, String signature) {
//return "Hi\n defaultText \n With best regards, $s";
        String messageTemplate = templateProvider.getMessageTemplate(templateName);
        if (messageTemplate == null || messageTemplate.isEmpty()) {
            throw new TemplateNotFoundException();
        }
        return String.format(messageTemplate, text, signature);
    }
}
