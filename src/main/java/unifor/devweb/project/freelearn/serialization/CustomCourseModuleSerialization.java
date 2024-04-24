package unifor.devweb.project.freelearn.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.RequiredArgsConstructor;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomCourseModuleSerialization extends JsonSerializer<CourseModule> {

    @Override
    public void serialize(CourseModule module, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {


        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", module.getId() != null ? module.getId() : 0);
        jsonGenerator.writeStringField("title", module.getTitle() != null ? module.getTitle() : "");
        jsonGenerator.writeStringField("description", module.getDescription() != null ? module.getDescription() : "");
        jsonGenerator.writeNumberField("sequenceNumber", module.getSequenceNumber());

        jsonGenerator.writeObjectField("course", module.getCourse() != null ? module.getCourse() : "{}");
        jsonGenerator.writeObjectField("lessons", module.getLessons() != null ? module.getLessons() : "{}");

        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
