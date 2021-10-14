package ch.ost.adunischatbot.model.fulfillment;

import java.util.Map;

public class ExmatriculationFulfillment {
    private String dateTime;
    private String studyEnd;

    private ExmatriculationFulfillment(String dateTime, String studyEnd) {
        this.dateTime = dateTime;
        this.studyEnd = studyEnd;
    }

    public static ExmatriculationFulfillment create(Map<String, String> fields) {
        return new ExmatriculationFulfillment(fields.get("date-time"), fields.get("studyEnd"));
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getStudyEnd() {
        return studyEnd;
    }

    @Override
    public String toString() {
        return "ExmatriculationFulfillment{" +
                "dateTime='" + dateTime + '\'' +
                ", studyEnd='" + studyEnd + '\'' +
                '}';
    }
}
