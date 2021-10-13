package ch.ost.adunischatbot.model;

import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.protobuf.Value;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * a Facade for the Query Result
 */
public class ChatBotAnswer {

    private QueryResult queryResult;

    public ChatBotAnswer(QueryResult queryResult) {
        this.queryResult = queryResult;
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


}
