package cacheit;

import io.micronaut.context.annotation.Prototype;
import io.micronaut.context.annotation.Value;
import io.micronaut.runtime.context.scope.Refreshable;

import java.time.ZonedDateTime;
import javax.inject.*;

@Prototype
public class BoringService {

    @Value("${simple-message:-nothing}")
    String baseMessage;

    public String getMessage() {
        return  baseMessage + " @ " + ZonedDateTime.now().toString();
    }
}
