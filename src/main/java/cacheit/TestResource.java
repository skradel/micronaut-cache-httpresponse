package cacheit;

import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.cache.annotation.Cacheable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;

import javax.inject.Inject;
import java.util.Optional;

@Controller()
@CacheConfig("thing")
public class TestResource {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TestResource.class);

    @Inject
    BoringService svc;

    /**
     * It always works
     *
     * @param x
     * @return
     */
    @Cacheable
    @Get(uri="simple", produces={MediaType.APPLICATION_JSON})
    public String simple(@QueryValue("x") Optional<String> x) {
        logger.info("called simple {}", x);
        return x + " " + svc.getMessage();
    }

    /**
     * Works OK when response is not in cache; dies horribly when response is in cache
     *
     * @param x
     * @return
     */
    @Cacheable
    @Get(uri="wrapped", produces={MediaType.APPLICATION_JSON})
    public HttpResponse<String> wrapped(@QueryValue("x") Optional<String> x) {
        logger.info("called cached {}", x);
        return HttpResponse.ok(x + " " + svc.getMessage());
    }
}
