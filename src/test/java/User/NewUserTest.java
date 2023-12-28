package User;

import utils.ExcelUtils;

import java.io.IOException;
import java.util.*;

public class NewUserTest {
    public Iterator<UserTest> getCreateUserData() throws IOException {
        List<LinkedHashMap<String,String>> excelDataAsListOfMap = ExcelUtils.getExcelDataAsListOfMap("CreateUserData","sheet1");
        List<UserTest> userData = new ArrayList<>();
        for (LinkedHashMap<String,String> data : excelDataAsListOfMap) {
            UserTest userTest = UserTest.builder()
                .id()
                .name()

        }
    }
}
