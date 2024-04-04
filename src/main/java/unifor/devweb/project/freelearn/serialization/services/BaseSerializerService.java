package unifor.devweb.project.freelearn.serialization.services;

import com.fasterxml.jackson.core.JsonGenerator;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

public abstract class BaseSerializerService {

    @Getter
    @Setter
    protected JsonGenerator jsonGenerator;

    protected BaseSerializerService(JsonGenerator jsonGenerator) {
        this.jsonGenerator = jsonGenerator;
    }

    protected BaseSerializerService() {
    }

    public  <T> void writeListField(String fieldName, List<T> entities) throws IOException {
        jsonGenerator.writeFieldName(fieldName);
        jsonGenerator.writeStartArray();
        if (entities != null) {
            for (T entity : entities) {
                Method getIdMethod;
                try {
                    getIdMethod = entity.getClass().getMethod("getId");
                    Object idValue = getIdMethod.invoke(entity);
                    if (idValue instanceof Long) {
                        jsonGenerator.writeNumber((Long) idValue);
                    } else if (idValue instanceof Integer) {
                        jsonGenerator.writeNumber((Integer) idValue);
                    } else {
                        jsonGenerator.writeNull();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    jsonGenerator.writeNull();
                }
            }
        }
        jsonGenerator.writeEndArray();
    }

    public <T> void writeField(String fieldName, T entity) throws IOException {
        jsonGenerator.writeFieldName(fieldName);
        if (entity != null) {
            Method getIdMethod;
            try {
                getIdMethod = entity.getClass().getMethod("getId");
                Object idValue = getIdMethod.invoke(entity);
                if (idValue instanceof Long) {
                    jsonGenerator.writeNumber((Long) idValue);
                } else if (idValue instanceof Integer) {
                    jsonGenerator.writeNumber((Integer) idValue);
                } else {
                    jsonGenerator.writeNull();
                }
            } catch (Exception e) {
                e.printStackTrace();
                jsonGenerator.writeNull();
            }
        } else {
            jsonGenerator.writeNull();
        }
    }
}
