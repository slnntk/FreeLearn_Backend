package unifor.devweb.project.freelearn;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Freelearn Project",
                version = "1.0.0",
                description = "Descrição da sua API aqui"
        )
)

public class FreelearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreelearnApplication.class, args);
    }

}
