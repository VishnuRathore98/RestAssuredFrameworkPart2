package User;

import User.pojos.UserPayloadAsPOJO;
import User.pojos.UserPoiji;
import com.poiji.bind.Poiji;
import io.restassured.response.Response;
import org.apache.poi.hssf.record.AutoFilterInfoRecord;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restUtils.AssertionUtils;
import restUtils.RestUtils;
import utils.ExcelUtils;
import utils.RandomDataGenerator;

import java.io.File;
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

    @DataProvider(name = "userDataPoiji")
    public Iterator<UserPayloadAsPOJO> getCreateUserDataPoiji(){
        List<UserPayloadAsPOJO> userData = Poiji.fromExcel(new File("/home/vpsr/IdeaProjects/RestAssuredFrameworkPart2/src/test/resources/testdata/CreateUserData.xlsx"), UserPayloadAsPOJO.class);
        return userData.iterator();
    }

    @Test(dataProvider = "userDataPoiji")
    public void createUserAndVerifyPoiji(UserPayloadAsPOJO payload){

//      TODO: Check if this is returning correct string data from excel or not.
        String cellValue = String.valueOf(payload.getIdValue());

        int size = 6;
        if(cellValue.contains("RandomId")){
//            With size
            if (cellValue.contains("_")) {
                size = Integer.parseInt((cellValue.split("_"))[1]);
            }
            cellValue = String.valueOf((RandomDataGenerator.getRandomNumber(size,size)));
        }
        payload.setId(Integer.parseInt(cellValue));

        Response response = RestUtils.performPost(endpoint,payload,new HashMap<>());
        Map<String, Object> expectedValuesMap = new HashMap<>();
        expectedValuesMap.put("id",payload.getId());
        expectedValuesMap.put("username",payload.getUserName());
        expectedValuesMap.put("firstName",payload.getFirstName());
        expectedValuesMap.put("lastName",payload.getLastName());
        expectedValuesMap.put("email",payload.getEmail());
        expectedValuesMap.put("password",payload.getPassword());
        expectedValuesMap.put("phone",payload.getPhone());
        expectedValuesMap.put("userStatus",payload.getUserStatus());

        AssertionUtils.assertExpectedValuesWithJsonPath(response,expectedValuesMap);
    }
}
