package unifor.devweb.project.freelearn.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import unifor.devweb.project.freelearn.domain.entities.Review;

import java.io.IOException;

public class CustomReviewSerialization extends JsonSerializer<Review> {

    @Override
    public void serialize(Review review, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", review.getId() != null ? review.getId() : 0);
        jsonGenerator.writeStringField("comment", review.getComment() != null ? review.getComment() : "");

        jsonGenerator.writeFieldName("student");
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", review.getStudent().getId() != null ? review.getStudent().getId() : 0);
        jsonGenerator.writeStringField("username", review.getStudent().getUser().getName());
        jsonGenerator.writeNumberField("hoursWatched", review.getStudent().getHoursWatched());
        jsonGenerator.writeNumberField("numberOfCoursesSubscribed", review.getStudent().getNumberOfCoursesSubscribed());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeFieldName("course");
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", review.getCourse().getId() != null ? review.getCourse().getId() : 0);
        jsonGenerator.writeStringField("title", review.getCourse().getTitle() != null ? review.getCourse().getTitle() : "");
        jsonGenerator.writeStringField("description", review.getCourse().getDescription() != null ? review.getCourse().getDescription() : "");
        jsonGenerator.writeNumberField("durationHours", review.getCourse().getDurationHours());
        jsonGenerator.writeStringField("link", review.getCourse().getLink() != null ? review.getCourse().getLink() : "");
        jsonGenerator.writeEndObject();

        jsonGenerator.writeFieldName("teacher");
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", review.getTeacher().getId() != null ? review.getTeacher().getId() : 0);
        jsonGenerator.writeStringField("username", review.getTeacher().getUser().getName());
        jsonGenerator.writeNumberField("hoursTaught", review.getTeacher().getHoursTaught());
        jsonGenerator.writeNumberField("overallRating", review.getTeacher().getOverallRating());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeEndObject();
    }
}
