package User;

import User.pojos.UserPayloadAsPOJO;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restUtils.AssertionUtils;
import restUtils.RestUtils;
import utils.ExcelUtils;

import java.io.IOException;
import java.util.*;

public class NewUserTest {

    String endpoint = "https://petstore.swagger.io/v2/user";

    @Test(dataProvider = "userDataProvider")
    public void createUserAndVerify(UserPayloadAsPOJO user){
//        UserPayloadAsPOJO request = new UserPayloadAsPOJO().toBuilder().firstName("John").build();
        Response response = RestUtils.performPost(endpoint,user,new HashMap<>());
        Map<String, Object> expectedValuesMap = new HashMap<>();
        expectedValuesMap.put("id",user.getId());
        expectedValuesMap.put("username",user.getUserName());
        expectedValuesMap.put("firstName",user.getFirstName());
        expectedValuesMap.put("lastName",user.getLastName());
        expectedValuesMap.put("email",user.getEmail());
        expectedValuesMap.put("password",user.getPassword());
        expectedValuesMap.put("phone",user.getPhone());
        expectedValuesMap.put("userStatus",user.getUserStatus());

        AssertionUtils.assertExpectedValuesWithJsonPath(response,expectedValuesMap);
    }

    @DataProvider(name = "userDataProvider")
    public Iterator<UserPayloadAsPOJO> getCreateUserData() throws IOException {
        List<LinkedHashMap<String,String>> excelDataAsListOfMap = ExcelUtils.getExcelDataAsListOfMap("CreateUserData","sheet1");
        List<UserPayloadAsPOJO> userData = new ArrayList<>();
        for (LinkedHashMap<String,String> data : excelDataAsListOfMap) {
            UserPayloadAsPOJO userTest = UserPayloadAsPOJO.builder()
                                            .id(Integer.parseInt(data.get("Id")))
                                            .userName(data.get("UserName"))
                                            .firstName(data.get("FirstName"))
                                            .lastName(data.get("LastName"))
                                            .email(data.get("Email"))
                                            .password(data.get("Password"))
                                            .phone(data.get("Phone"))
                                            .build();
            userData.add(userTest);
        }
        return userData.iterator();
    }
}
