package unifor.devweb.project.freelearn.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import unifor.devweb.project.freelearn.domain.entities.Course;
import com.fasterxml.jackson.databind.JsonSerializer;
import unifor.devweb.project.freelearn.domain.entities.CourseCategory;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;
import unifor.devweb.project.freelearn.domain.entities.Student;

import java.io.IOException;

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

        if (course.getTeacher() != null) {
            jsonGenerator.writeNumberField("teacherId", course.getTeacher().getId());
        } else {
            jsonGenerator.writeNullField("teacherId");
        }

        jsonGenerator.writeFieldName("moduleIds");
        jsonGenerator.writeStartArray();
        for (CourseModule module : course.getModules()) {
            jsonGenerator.writeNumber(module.getId());
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeFieldName("enrolledStudentIds");
        jsonGenerator.writeStartArray();
        for (Student student : course.getEnrolledStudents()) {
            jsonGenerator.writeNumber(student.getId());
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeFieldName("courseCategoryIds");
        jsonGenerator.writeStartArray();
        for (CourseCategory category : course.getCourseCategories()) {
            jsonGenerator.writeNumber(category.getId());
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}
