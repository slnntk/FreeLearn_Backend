package unifor.devweb.project.freelearn.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.RequiredArgsConstructor;
import unifor.devweb.project.freelearn.domain.entities.Student;
import unifor.devweb.project.freelearn.domain.entities.StudentCourse;
import unifor.devweb.project.freelearn.serialization.services.CustomSerializerService;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomStudentSerialization extends JsonSerializer<Student> {

    private final CustomSerializerService customSerializerService;

    @Override
    public void serialize(Student student, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        customSerializerService.setJsonGenerator(jsonGenerator);

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", student.getId() != null ? student.getId() : 0);
        jsonGenerator.writeStringField("username", student.getUser().getName());
        jsonGenerator.writeNumberField("hoursWatched", student.getHoursWatched());
        jsonGenerator.writeNumberField("numberOfCoursesSubscribed", student.getNumberOfCoursesSubscribed());

        customSerializerService.writeListField("enrolledCourses",
                student.getEnrolledCourses()
                        .stream()
                        .map(StudentCourse::getCourse).toList());
        customSerializerService.writeListField("reviewIds", student.getReviews());

        jsonGenerator.writeEndObject();
    }
}
