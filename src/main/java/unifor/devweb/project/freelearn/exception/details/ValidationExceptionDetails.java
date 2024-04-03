package unifor.devweb.project.freelearn.exception.details;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails {
    private String fields;
    private String fieldsMessage;
}