package ch.ost.adunischatbot.model;

import ch.ost.adunischatbot.model.fulfillment.*;
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
    Map<String, String> userFields;

    private CourseChangeFulfillment courseChangeFulfillment = null;
    private ExmatriculationFulfillment exmatriculationFulfillment = null;
    private PauseStudyFulfillment pauseStudyFulfillment= null;
    private SemesterReportFulfillment semesterReportFulfillment = null;
    private StudyReportFulfillment studyReportFulfillment = null;

    public ChatBotAnswer(DetectIntentResponse response) {
        this.queryResult = response.getQueryResult();
        this.outputAudio = response.getOutputAudio().toByteArray();
        userFields = convertToStringMap(response.getQueryResult().getParameters().getFieldsMap());
        if (hasAllNecessaryInformation()) {
            setFulfillmentInformation();
        }
    }

    private void setFulfillmentInformation() {
        switch (getIntend()) {
            case "stud.courseChange":
                courseChangeFulfillment = CourseChangeFulfillment.create(userFields);
                break;
            case "stud.exmatriculation":
                exmatriculationFulfillment = ExmatriculationFulfillment.create(userFields);
                break;
            case "stud.pauseStudy":
                pauseStudyFulfillment = PauseStudyFulfillment.create(userFields);
                break;
            case "rep.reportSemester":
                semesterReportFulfillment = SemesterReportFulfillment.create(userFields);
                break;
            case "rep.studyReport":
                studyReportFulfillment = StudyReportFulfillment.create(userFields);
                break;
            default:
                break; //unknown fulfillment, ignore
        }
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

    private static Map<String, String> convertToStringMap(Map<String, Value> fieldsMap) {
        return fieldsMap.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> toString(e.getValue())));
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

    public CourseChangeFulfillment getCourseChangeFulfillment() {
        return courseChangeFulfillment;
    }

    public ExmatriculationFulfillment getExmatriculationFulfillment() {
        return exmatriculationFulfillment;
    }

    public PauseStudyFulfillment getPauseStudyFulfillment() {
        return pauseStudyFulfillment;
    }

    public SemesterReportFulfillment getSemesterReportFulfillment() {
        return semesterReportFulfillment;
    }

    public StudyReportFulfillment getStudyReportFulfillment() {
        return studyReportFulfillment;
    }
}
