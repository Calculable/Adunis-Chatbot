package ch.ost.adunischatbot.model.fulfillment;

import java.util.Map;
import java.util.Objects;

public class CourseChangeFulfillment {
    private String course;
    private String dateTime;

    private CourseChangeFulfillment(String course, String dateTime) {
        this.course = course;
        this.dateTime = dateTime;
    }

    public static CourseChangeFulfillment create(Map<String, String> fields) {
        return new CourseChangeFulfillment(fields.get("course"), fields.get("date-time"));
    }

    public String getCourse() {
        return course;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "CourseChangeFulfillment{" +
                "course='" + course + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
