package unifor.devweb.project.freelearn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import unifor.devweb.project.freelearn.domain.entities.*;
import unifor.devweb.project.freelearn.repository.*;

import java.util.Arrays;

//@Configuration
public class DataInitializationConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseCategoryRepository courseCategoryRepository;

    @Autowired
    private CourseModuleRepository courseModuleRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Bean
    public CommandLineRunner dataInitializer() {
        return null;
    }
}