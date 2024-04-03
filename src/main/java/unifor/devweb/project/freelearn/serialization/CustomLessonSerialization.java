package unifor.devweb.project.freelearn.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import unifor.devweb.project.freelearn.domain.entities.Lesson;

import java.io.IOException;

public class CustomLessonSerialization extends JsonSerializer<Lesson> {

    @Override
    public void serialize(Lesson lesson, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", lesson.getId() != null ? lesson.getId() : 0);
        jsonGenerator.writeStringField("title", lesson.getTitle() != null ? lesson.getTitle() : "");
        jsonGenerator.writeStringField("videoUrl", lesson.getVideoUrl() != null ? lesson.getVideoUrl() : "");
        jsonGenerator.writeNumberField("durationMinutes", lesson.getDurationMinutes());

        jsonGenerator.writeObjectField("moduleId", lesson.getModule().getId() != null ? lesson.getModule().getId() : 0);
        jsonGenerator.writeObjectField("courseId", lesson.getModule().getCourse().getId() != null ? lesson.getModule().getCourse().getId() : 0);

        jsonGenerator.writeEndObject();
    }
}
