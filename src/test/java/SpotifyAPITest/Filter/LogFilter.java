package SpotifyAPITest.Filter;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(LogFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {
        logRequest(filterableRequestSpecification);
        Response response = filterContext.next(filterableRequestSpecification,filterableResponseSpecification);
        logResponse(response);
        return response;
    }

    private void logRequest(FilterableRequestSpecification filterableRequestSpecification){
        logger.info("Request headers are ; {}", filterableRequestSpecification.getHeaders());
        logger.info("Request Body is : " + filterableRequestSpecification.getBody());
        logger.info("Request Endpoint is ; {}", filterableRequestSpecification.getURI());
    }
    private void logResponse(Response response){
        logger.info("Response status code is :  {}", response.getStatusCode());
        logger.info("Response body is : {}", response.getBody());
    }
}
