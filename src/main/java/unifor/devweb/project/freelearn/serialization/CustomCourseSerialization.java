package unifor.devweb.project.freelearn.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.JsonSerializer;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.domain.entities.CourseCategory;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;
import unifor.devweb.project.freelearn.domain.entities.StudentCourse;

import java.io.IOException;

public class CustomCourseSerialization extends JsonSerializer<Course> {

    @Override
    public void serialize(Course course, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        System.out.println(course);
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", course.getId() != null ? course.getId() : 0);
        jsonGenerator.writeStringField("title", course.getTitle() != null ? course.getTitle() : "");
        jsonGenerator.writeStringField("description", course.getDescription() != null ? course.getDescription() : "");
        jsonGenerator.writeStringField("imageUrl", course.getImageUrl() != null ? course.getImageUrl() : "");
        jsonGenerator.writeStringField("language", course.getLanguage() != null ? course.getLanguage() : "");
        jsonGenerator.writeNumberField("durationHours", course.getDurationHours());
        jsonGenerator.writeStringField("link", course.getLink() != null ? course.getLink() : "");

        jsonGenerator.writeNumberField("teacherId", course.getTeacher() != null && course.getTeacher().getId() != null ? course.getTeacher().getId() : 0);

        jsonGenerator.writeFieldName("moduleIds");
        jsonGenerator.writeStartArray();
        if (course.getModules() != null) {
            for (CourseModule module : course.getModules()) {
                jsonGenerator.writeNumber(module.getId() != null ? module.getId() : 0);
            }
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeFieldName("enrolledStudentIds");
        jsonGenerator.writeStartArray();
        if (course.getEnrolledStudents() != null) {
            for (StudentCourse studentCourse : course.getEnrolledStudents()) {
                System.out.println(studentCourse);
                jsonGenerator.writeNumber(studentCourse.getStudent() != null && studentCourse.getStudent().getId() != null ? studentCourse.getStudent().getId() : 0);
            }
        }
        jsonGenerator.writeEndArray();


        jsonGenerator.writeFieldName("courseCategoryIds");
        jsonGenerator.writeStartArray();
        if (course.getCourseCategories() != null) {
            for (CourseCategory category : course.getCourseCategories()) {
                jsonGenerator.writeNumber(category.getId() != null ? category.getId() : 0);
            }
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}
