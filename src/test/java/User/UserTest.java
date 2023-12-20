package User;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import restUtils.RestUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    Faker javafaker = new Faker();
    String userName = javafaker.name().username();

//  Updated version of java faker
    net.datafaker.Faker datafaker = new net.datafaker.Faker();

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
        Response response = RestUtils.performPost(endpoint,Payloads.getMapData(javafaker.number().digits(4),userName,javafaker.name().firstName(),javafaker.name().lastName(),javafaker.internet().emailAddress(), RandomStringUtils.randomAlphanumeric(8),datafaker.phoneNumber().cellPhone(),"1"),new HashMap<>());
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test(priority = 2)
    void getUser(){
        String endpoint = "https://petstore.swagger.io/v2/user/{userName}";
        Map<String,String> param = new HashMap<>();
        param.put("userName",userName);

        Response response = RestUtils.performGet(endpoint,"",param);
        Assert.assertEquals(response.statusCode(),200);
    }
}
