package restUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public class RestUtils {
    public static Response performPost(String endpoint, String payload, Map<String,String> headers){
        return RestAssured.given().log().all()
            .headers(headers)
            .contentType(ContentType.JSON)
//            .pathParam()
            .body(payload)
            .post(endpoint)
            .then().log().all().extract().response();

    }

    public static Response performPost(String endpoint, Map<String,Object> payload, Map<String,String> headers){
        return RestAssured.given().log().all()
            .headers(headers)
            .contentType(ContentType.JSON)
//            .pathParam()
            .body(payload)
            .post(endpoint)
            .then().log().all().extract().response();

    }
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

