package unifor.devweb.project.freelearn.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.RequiredArgsConstructor;
import unifor.devweb.project.freelearn.domain.entities.CourseCategory;
import unifor.devweb.project.freelearn.domain.entities.CourseCourseCategory;
import unifor.devweb.project.freelearn.serialization.services.CustomSerializerService;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomCourseCategorySerialization extends JsonSerializer<CourseCategory> {

    private final CustomSerializerService customSerializerService;
    @Override
    public void serialize(CourseCategory category, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        customSerializerService.setJsonGenerator(jsonGenerator);


        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", category.getId() != null ? category.getId() : 0);
        jsonGenerator.writeStringField("name", category.getName() != null ? category.getName() : "");
        jsonGenerator.writeStringField("description", category.getDescription() != null ? category.getDescription() : "");

        if (category.getCourses() != null){
            customSerializerService.writeListField("courseIds",
                    category.getCourses()
                            .stream()
                            .map(CourseCourseCategory::getCourse).toList());
        }

        jsonGenerator.writeEndObject();
    }
}
