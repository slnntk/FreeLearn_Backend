package unifor.devweb.project.freelearn.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.JsonSerializer;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.domain.entities.CourseCategory;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;
import unifor.devweb.project.freelearn.domain.entities.Student;
import unifor.devweb.project.freelearn.domain.entities.Teacher;

import java.io.IOException;
import java.util.List;

public class CustomCourseSerialization extends JsonSerializer<Course> {

    @Override
    public void serialize(Course course, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", course.getId());
        jsonGenerator.writeStringField("title", course.getTitle());
        jsonGenerator.writeStringField("description", course.getDescription());
        jsonGenerator.writeStringField("imageUrl", course.getImageUrl());
        jsonGenerator.writeStringField("language", course.getLanguage());
        jsonGenerator.writeNumberField("durationHours", course.getDurationHours());
        jsonGenerator.writeStringField("link", course.getLink());

        Teacher teacher = course.getTeacher();
        if (teacher != null) {
            jsonGenerator.writeNumberField("teacherId", teacher.getId());
        } else {
            jsonGenerator.writeNullField("teacherId");
        }

        writeArrayField("moduleIds", course.getModules(), jsonGenerator);
        writeArrayField("enrolledStudentIds", course.getEnrolledStudents(), jsonGenerator);
        writeArrayField("courseCategoryIds", course.getCourseCategories(), jsonGenerator);

        jsonGenerator.writeEndObject();
    }

    private <T> void writeArrayField(String fieldName, List<T> entities, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeFieldName(fieldName);
        jsonGenerator.writeStartArray();
        for (T entity : entities) {
            if (entity instanceof Long) {
                jsonGenerator.writeNumber((Long) entity);
            }
        }
        jsonGenerator.writeEndArray();
    }
}
