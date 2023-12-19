package restUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import reporting.ExtentReportManager;

import java.util.Map;

public class RestUtils {

//  Returns RequestSpecification
    private static RequestSpecification getRequestSpecification(String endpoint, Object requestPayload, Map<String,String> headers){
        return RestAssured.given()
                            .baseUri(endpoint)
                            .headers(headers)
                            .contentType(ContentType.JSON)
                            .body(requestPayload);
    }

//  Get RequestSpecification and returns post()
    public static Response performPost(String endpoint, String payload, Map<String,String> headers){
        RequestSpecification requestSpecification = getRequestSpecification(endpoint,payload,headers);
        Response response = requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

//  Get RequestSpecification and returns post()
    public static Response performPost(String endpoint, Map<String,Object> payload, Map<String,String> headers){
        RequestSpecification requestSpecification = getRequestSpecification(endpoint,payload,headers);
        Response response = requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

    public static void printRequestLogInReport(RequestSpecification requestSpecification){
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        ExtentReportManager.logInfoDetails("Endpoint is: "+queryableRequestSpecification.getBaseUri());
        ExtentReportManager.logInfoDetails("Method is: "+queryableRequestSpecification.getMethod());
//      Printing headers
        ExtentReportManager.logInfoDetails("Headers are: ");
        ExtentReportManager.printHeaders(queryableRequestSpecification.getHeaders().asList());
//      Printing JSON
        ExtentReportManager.logInfoDetails("Request body is: ");
        ExtentReportManager.logJson(queryableRequestSpecification.getBody());
    }

    public static void printResponseLogInReport(Response response){
        ExtentReportManager.logInfoDetails("Response status is: "+response.getStatusCode());
//      Printing Headers
        ExtentReportManager.logInfoDetails("Response headers are: ");
        ExtentReportManager.printHeaders(response.getHeaders().asList());

//      Printing JSON
        ExtentReportManager.logInfoDetails("Response body is: ");
        ExtentReportManager.logJson(response.getBody().prettyPrint());
    }


//  Need to Implement RequestSpecification.
    public static Response performGet(String endpoint, String userName,Map<String,String> params){
//        Map.Entry<String, String> entry = params.entrySet().iterator().next();
//        String key = entry.getKey();
//        String value = entry.getValue();
        return RestAssured.given().log().all()
            .pathParam(params.entrySet().iterator().next().getKey(),params.entrySet().iterator().next().getValue())
            .contentType(ContentType.JSON)
            .get(endpoint)
            .then().log().all().extract().response();
    }
}

