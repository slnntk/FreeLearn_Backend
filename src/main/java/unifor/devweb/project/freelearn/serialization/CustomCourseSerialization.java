package unifor.devweb.project.freelearn.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.RequiredArgsConstructor;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.domain.entities.CourseCourseCategory;
import unifor.devweb.project.freelearn.domain.entities.StudentCourse;
import unifor.devweb.project.freelearn.serialization.services.CustomSerializerService;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomCourseSerialization extends JsonSerializer<Course> {

    private final CustomSerializerService customSerializerService;

    @Override
    public void serialize(Course course, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        customSerializerService.setJsonGenerator(jsonGenerator);

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", course.getId() != null ? course.getId() : 0);
        jsonGenerator.writeStringField("title", course.getTitle() != null ? course.getTitle() : "");
        jsonGenerator.writeStringField("description", course.getDescription() != null ? course.getDescription() : "");
        jsonGenerator.writeStringField("imageUrl", course.getImageUrl() != null ? course.getImageUrl() : "");
        jsonGenerator.writeStringField("language", course.getLanguage() != null ? course.getLanguage() : "");
        jsonGenerator.writeNumberField("durationHours", course.getDurationHours());
        jsonGenerator.writeStringField("link", course.getLink() != null ? course.getLink() : "");

        customSerializerService.writeListField("moduleIds", course.getModules());

        if (course.getEnrolledStudents() != null) {
            customSerializerService.writeListField("enrolledStudentIds", course.getEnrolledStudents().stream().map(StudentCourse::getStudent).toList());
        }

        customSerializerService.writeListField("courseCategoryIds", course.getCourseCategories().stream().map(CourseCourseCategory::getCategory).toList());
        customSerializerService.writeField("teacherId", course.getTeacher());

        jsonGenerator.writeEndObject();
    }
}
