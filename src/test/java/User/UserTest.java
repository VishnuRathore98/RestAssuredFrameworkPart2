package User;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import restUtils.RestUtils;
import utils.JsonUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    Faker faker = new Faker();
    String userName = faker.name().username();

    // Creating a new user
    @Test
    void createUser() throws IOException {
//        The following lines of commented code can be used to define and use User data based on env
//        like different type of data for Dev, QA, and Prod.
        
//        String env = System.getProperty("env") == null ? "QA" : System.getProperty("env");
//        Map<String,String> data = JsonUtils.getJsonDataAsMap("Users/"+env+"UsersAPIData.json");
//        String endpoint = data.get("createUsersEndpoint");
//        Map<String,String> payload = Payloads.getMapData("","","","","","","","");

        String endpoint = "https://petstore.swagger.io/v2/user";
        Response response = RestUtils.performPost(endpoint,Payloads.getMapData(faker.number().digits(4),userName,faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),faker.internet().password(),faker.phoneNumber().cellPhone(),"1"),new HashMap<>());
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    void getUser(){
        String endpoint = "https://petstore.swagger.io/v2/user/{userName}";
        Map<String,String> param = new HashMap<>();
        param.put("userName",userName);

        Response response = RestUtils.performGet(endpoint,"",param);
        Assert.assertEquals(response.statusCode(),200);
    }
}
