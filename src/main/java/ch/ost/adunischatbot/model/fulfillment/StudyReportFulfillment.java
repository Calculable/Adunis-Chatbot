package ch.ost.adunischatbot.model.fulfillment;

import java.util.Map;

public class StudyReportFulfillment {
    private String report;

    private StudyReportFulfillment(String report) {
        this.report = report;
    }

    public static StudyReportFulfillment create(Map<String, String> fields) {
        return new StudyReportFulfillment(fields.get("report"));
    }

    public String getReport() {
        return report;
    }

    @Override
    public String toString() {
        return "StudyReportFulfillment{" +
                "report='" + report + '\'' +
                '}';
    }
}
