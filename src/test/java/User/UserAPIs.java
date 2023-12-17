package User;

import io.restassured.response.Response;
import restUtils.RestUtils;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class UserAPIs {
    public Response createUser(Map<String,Object> createUserPayload){
        String endpoint = (String) Base.dataFromJsonFile.get("create_user");
        return RestUtils.performPost(endpoint,createUserPayload,new HashMap<>());
    }
}
