package ch.ost.adunischatbot.service;

import ch.ost.adunischatbot.model.ChatBotAnswer;
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

    public ChatBotAnswer sendMessage(String userMessage) {

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
        return new ChatBotAnswer(response);
    }

}
