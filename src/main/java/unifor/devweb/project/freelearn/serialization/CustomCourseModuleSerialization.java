package unifor.devweb.project.freelearn.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;
import unifor.devweb.project.freelearn.domain.entities.Lesson;

import java.io.IOException;

public class CustomCourseModuleSerialization extends JsonSerializer<CourseModule> {

    @Override
    public void serialize(CourseModule module, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", module.getId());
        jsonGenerator.writeStringField("title", module.getTitle());
        jsonGenerator.writeStringField("description", module.getDescription());
        jsonGenerator.writeNumberField("sequenceNumber", module.getSequenceNumber());

        jsonGenerator.writeNumberField("courseId", module.getCourse().getId());

        jsonGenerator.writeFieldName("lessonIds");
        jsonGenerator.writeStartArray();
        for (Lesson lesson : module.getLessons()) {
            jsonGenerator.writeNumber(lesson.getId());
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}
