package unifor.devweb.project.freelearn.serialization.services;


import com.fasterxml.jackson.core.JsonGenerator;
import org.springframework.stereotype.Component;

@Component
public class CustomSerializerService extends BaseSerializerService {

    public CustomSerializerService() {
    }

    public CustomSerializerService(JsonGenerator jsonGenerator) {
        super(jsonGenerator);
    }
}
