package ch.ost.adunischatbot.model;

import ch.ost.adunischatbot.service.SoundPlayer;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.protobuf.Value;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * a Facade for the Query Result
 */
public class ChatBotAnswer {

    private QueryResult queryResult;
    private byte[] outputAudio;

    public ChatBotAnswer(DetectIntentResponse response) {
        this.queryResult = response.getQueryResult();
        this.outputAudio = response.getOutputAudio().toByteArray();
    }

    public float getSentimentScore() {
        return queryResult.getSentimentAnalysisResult().getQueryTextSentiment().getScore();
    }

    public float getIntendConfidenceScore() {
        return queryResult.getIntentDetectionConfidence();
    }

    public String getAnswerMessage() {
        return queryResult.getFulfillmentText();
    }

    public String getUserMessage() {
        return queryResult.getQueryText();
    }

    public Map<String, String> getUserDataFields() {
        return queryResult.getParameters().getFieldsMap().entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> toString(e.getValue())));
    }

    private static String toString(Value value) {
        return value.getStringValue();
    }

    public boolean hasAllNecessaryInformation() {
        return queryResult.getAllRequiredParamsPresent();
    }

    public String getIntend() {
        return queryResult.getIntent().getDisplayName();
    }

    public  byte[] getOutputAudio() {
        return outputAudio;
    }
}
