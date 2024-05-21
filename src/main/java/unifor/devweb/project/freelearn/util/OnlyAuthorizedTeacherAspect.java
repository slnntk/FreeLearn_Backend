package unifor.devweb.project.freelearn.util;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import unifor.devweb.project.freelearn.services.CourseService;

@Aspect
@Log4j2
@Component
@RequiredArgsConstructor
public class OnlyAuthorizedTeacherAspect {

    private final CourseService courseService;

    @Before("@annotation(unifor.devweb.project.freelearn.util.OnlyAuthorizedTeacher) && args(id,..)")
    public void beforeMethodWithId(Long id) {

        log.info("Método anotado com @OnlyAuthorizedTeacher está prestes a ser executado!");
        log.info("ID recebido do PathVariable: " + id);

        courseService.canAuthenticatedUserToModifyThisCourse(id);


    }
}

//@Aspect
//@Component
//@RequiredArgsConstructor
//public class OnlyAuthorizedTeacherAspect {
//
//    private final CourseService courseService;
//
//    @Before("@annotation(OnlyAuthorizedTeacher) && args(id,..)")
//    public void beforeMethodWithId(JoinPoint joinPoint, Long id) {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        OnlyAuthorizedTeacher annotation = method.getAnnotation(OnlyAuthorizedTeacher.class);
//
//        System.out.println("Método anotado com @OnlyAuthorizedTeacher está prestes a ser executado!");
//        System.out.println("ID recebido do PathVariable: " + id);
//
//        courseService.canAuthenticatedUserToModifyThisCourse(id);
//
//
//    }
//}

