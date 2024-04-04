package unifor.devweb.project.freelearn.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.RequiredArgsConstructor;
import unifor.devweb.project.freelearn.domain.entities.Teacher;
import unifor.devweb.project.freelearn.serialization.services.CustomSerializerService;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomTeacherSerialization extends JsonSerializer<Teacher> {

    private final CustomSerializerService customSerializerService;

    @Override
    public void serialize(Teacher teacher, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        customSerializerService.setJsonGenerator(jsonGenerator);

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", teacher.getId() != null ? teacher.getId() : 0);
        jsonGenerator.writeStringField("username", teacher.getUser().getName());
        jsonGenerator.writeStringField("employeeId", teacher.getEmployeeId() != null ? teacher.getEmployeeId() : "");
        jsonGenerator.writeObjectField("areasOfExpertise", teacher.getAreasOfExpertise());
        jsonGenerator.writeNumberField("overallRating", teacher.getOverallRating());
        jsonGenerator.writeNumberField("hoursTaught", teacher.getHoursTaught());

        customSerializerService.writeListField("courseIds", teacher.getCourses());

        jsonGenerator.writeEndObject();
    }
}

