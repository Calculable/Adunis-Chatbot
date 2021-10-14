package ch.ost.adunischatbot.model.fulfillment;

import java.util.Map;

public class PauseStudyFulfillment {
    private String semester;
    private String year;

    private PauseStudyFulfillment(String semester, String year) {
        this.semester = semester;
        this.year = year;
    }

    public static PauseStudyFulfillment create(Map<String, String> fields) {
        return new PauseStudyFulfillment(fields.get("semester"), fields.get("year"));
    }

    public String getSemester() {
        return semester;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "PauseStudyFulfillment{" +
                "semester='" + semester + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
