package ch.ost.adunischatbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.cloud.dialogflow.v2.*;

import java.io.IOException;
import java.util.UUID;

@Service
public class ChatBotService {

    private final static String PROJECT_ID = "adunis-ryej";
    private final static String LANGUAGE_CODE = "de-CH";

    private SessionsClient sessionsClient;
    private SessionName session;

    @Autowired
    public ChatBotService() throws IOException {
        sessionsClient = SessionsClient.create();
        session = SessionName.of(PROJECT_ID, UUID.randomUUID().toString());
    }

    public String sendMessage(String userMessage) {
        TextInput.Builder textInput = TextInput.newBuilder().setText(userMessage).setLanguageCode(LANGUAGE_CODE);
        QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();
        DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
        QueryResult queryResult = response.getQueryResult();
        return queryResult.getFulfillmentText();
    }
}
