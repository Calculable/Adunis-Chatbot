package ch.ost.adunischatbot.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatHistoryMessage {

    private Date dateTime;
    private String message;
    private boolean userMessage;

    public ChatHistoryMessage(String message, boolean isUserMessage) {
        this.dateTime = new Date();
        this.message = message;
        this.userMessage = isUserMessage;
    }

    public String getDisplayTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");//dd/MM/yyyy
        return dateFormat.format(dateTime);
    }

    public String getMessage() {
        return message;
    }

    public boolean isUserMessage() {
        return userMessage;
    }
}
