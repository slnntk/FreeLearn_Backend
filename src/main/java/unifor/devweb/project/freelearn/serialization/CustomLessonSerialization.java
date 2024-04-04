package unifor.devweb.project.freelearn.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.RequiredArgsConstructor;
import unifor.devweb.project.freelearn.domain.entities.Lesson;
import unifor.devweb.project.freelearn.serialization.services.CustomSerializerService;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomLessonSerialization extends JsonSerializer<Lesson> {

    private final CustomSerializerService customSerializerService;

    @Override
    public void serialize(Lesson lesson, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        customSerializerService.setJsonGenerator(jsonGenerator);

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", lesson.getId() != null ? lesson.getId() : 0);
        jsonGenerator.writeStringField("title", lesson.getTitle() != null ? lesson.getTitle() : "");
        jsonGenerator.writeStringField("videoUrl", lesson.getVideoUrl() != null ? lesson.getVideoUrl() : "");
        jsonGenerator.writeNumberField("durationMinutes", lesson.getDurationMinutes());

        customSerializerService.writeField("courseId", lesson.getModule().getCourse());
        customSerializerService.writeField("moduleId", lesson.getModule());

        jsonGenerator.writeEndObject();
    }
}
