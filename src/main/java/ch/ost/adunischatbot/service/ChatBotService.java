package ch.ost.adunischatbot.service;

import ch.ost.adunischatbot.model.ChatBotAnswer;
import ch.ost.adunischatbot.model.ChatHistoryMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.cloud.dialogflow.v2.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ChatBotService {

    private final static String PROJECT_ID = "adunis-ryej";
    private final static String LANGUAGE_CODE = "de-CH";

    private SessionsClient sessionsClient;
    private SessionName session;

    private List<ChatHistoryMessage> chatHistory;

    @Autowired
    public ChatBotService() throws IOException {
        chatHistory = new ArrayList<>();
        sessionsClient = SessionsClient.create();
        session = SessionName.of(PROJECT_ID, UUID.randomUUID().toString());
    }

    public ChatBotAnswer sendMessage(String userMessage) {

        chatHistory.add(new ChatHistoryMessage(userMessage, true));

        TextInput.Builder textInput = TextInput.newBuilder()
                .setText(userMessage)
                .setLanguageCode(LANGUAGE_CODE);

        QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

        //see https://github.com/dialogflow/dialogflow-java-client-v2/blob/master/samples/src/main/java/com/example/dialogflow/DetectIntentTTSResponses.java
        OutputAudioEncoding audioEncoding = OutputAudioEncoding.OUTPUT_AUDIO_ENCODING_LINEAR_16;
        int sampleRateHertz = 16000;
        OutputAudioConfig outputAudioConfig =
                OutputAudioConfig.newBuilder()
                        .setAudioEncoding(audioEncoding)
                        .setSampleRateHertz(sampleRateHertz)
                        .build();

        DetectIntentRequest detectIntentRequest =
                DetectIntentRequest.newBuilder()
                        .setQueryInput(queryInput)
                        .setOutputAudioConfig(outputAudioConfig)
                        .setSession(session.toString())
                        .build();

        DetectIntentResponse response = sessionsClient.detectIntent(detectIntentRequest);
        ChatBotAnswer answer = new ChatBotAnswer(response);
        chatHistory.add(new ChatHistoryMessage(answer.getAnswerMessage(), false));
        return answer;
    }

    public List<ChatHistoryMessage> getChatHistory() {
        return chatHistory;
    }
}
