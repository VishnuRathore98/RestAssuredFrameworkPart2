package User;

//import com.github.javafaker.Faker;
//import net.datafaker.Faker;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import restUtils.RestUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//import static User.Payloads.userName;

public class UserTest {

    // Creating a new user
    @Test(priority = 1)
    void createUser() throws IOException {
//        The following lines of commented code can be used to define and use User data based on env
//        like different type of data for Dev, QA, and Prod.
        
//        String env = System.getProperty("env") == null ? "QA" : System.getProperty("env");
//        Map<String,String> data = JsonUtils.getJsonDataAsMap("Users/"+env+"UsersAPIData.json");
//        String endpoint = data.get("createUsersEndpoint");
//        Map<String,String> payload = Payloads.getMapData("","","","","","","","");

        String endpoint = "https://petstore.swagger.io/v2/user";
        Response response = RestUtils.performPost(endpoint,Payloads.getCreateUserPayloadFromPOJO(),new HashMap<>());
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test(priority = 2)
    void getUser(){
        String endpoint = "https://petstore.swagger.io/v2/user/";
        Map<String,String> param = new HashMap<>();
        param.put("userName",Payloads.getCreateUserPayloadFromPOJO().getUserName());

        Response response = RestUtils.performGet(endpoint,Payloads.getCreateUserPayloadFromPOJO().getUserName(),new HashMap<>());
        Assert.assertEquals(response.statusCode(),200);
    }
}
