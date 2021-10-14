package ch.ost.adunischatbot.model.fulfillment;

import java.util.Map;

public class SemesterReportFulfillment {
    private String reportSemester;
    private String semester;
    private String year;

    private SemesterReportFulfillment(String reportSemester, String semester, String year) {
        this.reportSemester = reportSemester;
        this.semester = semester;
        this.year = year;
    }

    public static SemesterReportFulfillment create(Map<String, String> fields) {
        return new SemesterReportFulfillment(fields.get("reportSemester"), fields.get("semester"), fields.get("year"));
    }

    public String getReportSemester() {
        return reportSemester;
    }

    public String getSemester() {
        return semester;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "SemesterReportFulfillment{" +
                "reportSemester='" + reportSemester + '\'' +
                ", semester='" + semester + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
