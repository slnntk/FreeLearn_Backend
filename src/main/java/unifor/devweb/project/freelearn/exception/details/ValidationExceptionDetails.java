package unifor.devweb.project.freelearn.exception.details;


import lombok.Getter;
import lombok.experimental.SuperBuilder;
import unifor.devweb.project.freelearn.exception.details.ExceptionDetails;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails {
    private String fields;
    private String fieldsMessage;
}