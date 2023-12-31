package User;

import User.pojos.CreateUser;
import User.pojos.UserPayloadAsPOJO;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import reporting.Setup;
import restUtils.AssertionUtils;
import restUtils.RestUtils;
import utils.ExcelUtils;
import java.io.IOException;
import java.util.*;

public class UserTestScenario {

    String endpoint = "https://petstore.swagger.io/v2/user";

    @Test(dataProvider = "userDataProvider")
    public void createUserAndVerify(CreateUser user){
//      Printing test scenario Id as the test name and test scenario description as test description.
        ExtentTest test = Setup.extentReports.createTest("Test Name "+user.getScenarioId(), user.getScenarioDec());
        Setup.extentTest.set(test);

        Response response = RestUtils.performPost(endpoint,user,new HashMap<>());

        if (user.getExpectedStatusCode() != 200) {
            if (user.getScenarioId().equalsIgnoreCase("CreateUser_DuplicateId")) {
                response = RestUtils.performPost(endpoint,user,new HashMap<>());
            }
            Assert.assertEquals(response.getStatusCode(), user.getExpectedStatusCode());
//            Assert what error message we should get for using duplicate user Id.

//            Assert.assertEquals(response.jsonPath().getString(""), user.getExpectedErrorMessage());
        }
        else {

            Map<String, Object> expectedValuesMap = new HashMap<>();
            expectedValuesMap.put("id", user.getId());
            expectedValuesMap.put("username", user.getUserName());
            expectedValuesMap.put("firstName", user.getFirstName());
            expectedValuesMap.put("lastName", user.getLastName());
            expectedValuesMap.put("email", user.getEmail());
            expectedValuesMap.put("password", user.getPassword());
            expectedValuesMap.put("phone", user.getPhone());
            expectedValuesMap.put("userStatus", user.getUserStatus());

            if (user.getScenarioId().equalsIgnoreCase("CreateUser_WithoutId")) {
                expectedValuesMap.remove("Id");
                AssertionUtils.assertExpectedValuesWithJsonPath(response, expectedValuesMap);
            }
            AssertionUtils.assertExpectedValuesWithJsonPath(response, expectedValuesMap);
        }
    }

    @DataProvider(name = "userDataProvider")
    public Iterator<CreateUser> getCreateUserData() throws IOException {
        List<LinkedHashMap<String,String>> excelDataAsListOfMap = ExcelUtils.getExcelDataAsListOfMap("CreateUserData","sheet1");
        List<CreateUser> userData = new ArrayList<>();
        for (LinkedHashMap<String,String> data : excelDataAsListOfMap) {
            CreateUser userTest = getCustomizedUserData(data);
            userData.add(userTest);
        }
        return userData.iterator();
    }

    private CreateUser getCustomizedUserData(LinkedHashMap<String ,String> data){
        CreateUser createUser = new CreateUser();
        createUser.setScenarioId(data.get("TestScenario"));
        createUser.setScenarioDec(data.get("ScenarioDescription"));
        createUser.setExpectedStatusCode(Integer.parseInt(data.get("ExpectedStatusCode")));

        if(!data.get("ExpectedErrorMessage").equals("NO_ERROR"))
            createUser.setExpectedErrorMessage(data.get("ExpectedErrorMessage"));
        if(!data.get("Id").equals("NO_DATA"))
            createUser.setExpectedErrorMessage(data.get("Id"));
        if(!data.get("UserName").equals("NO_DATA"))
            createUser.setExpectedErrorMessage(data.get("UserName"));
        if(!data.get("FirstName").equals("NO_DATA"))
            createUser.setExpectedErrorMessage(data.get("FirstName"));
        if(!data.get("LastName").equals("NO_DATA"))
            createUser.setExpectedErrorMessage(data.get("LastName"));
        if(!data.get("Email").equals("NO_DATA"))
            createUser.setExpectedErrorMessage(data.get("Email"));
        if(!data.get("Password").equals("NO_DATA"))
            createUser.setExpectedErrorMessage(data.get("Password"));
        if(!data.get("Phone").equals("NO_DATA"))
            createUser.setExpectedErrorMessage(data.get("Phone"));

        return createUser;
    }

}