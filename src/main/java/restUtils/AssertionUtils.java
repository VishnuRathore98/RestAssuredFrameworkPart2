// This class is for printing response and requests side by side in a tabular format and
// further classifying whether both matches or not.
// That is, whether the actual value is same as expected value.

package restUtils;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.response.Response;
import reporting.ExtentReportManager;
import reporting.Setup;

import java.util.*;

public class AssertionUtils {

    public static void assertExpectedValuesWithJsonPath(Response response, Map<String, Object> expectedValuesMap){
        List<AssertionKeys> actualValuesMap = new ArrayList<>();
        actualValuesMap.add(new AssertionKeys("JSON_PATH","EXPECTED_VALUE","ACTUAL_VALUE","RESULT"));
        boolean allMatched = true;

        Set<String> jsonPaths = expectedValuesMap.keySet();
        for (String jsonPath: jsonPaths){
            Optional<Object> actualValue = Optional.ofNullable(response.jsonPath().get(jsonPath));
            if (actualValue.isPresent()){
                Object value = actualValue.get();
                    if (value.equals(expectedValuesMap.get(jsonPath)))
                        actualValuesMap.add(new AssertionKeys(jsonPath, expectedValuesMap.get(jsonPath), value, "MATCHED"));
                    else {
                        allMatched = false;
                        actualValuesMap.add(new AssertionKeys(jsonPath, expectedValuesMap.get(jsonPath),value,"NOT_MATCHED"));
                    }
            }
            else {
                allMatched = false;
                actualValuesMap.add(new AssertionKeys(jsonPath,expectedValuesMap.get(jsonPath),"VALUE_NOT_FOUND","NOT_MATCHED"));
            }
        }
        if (allMatched){
            ExtentReportManager.logPassDetails("All assertion passed. 😀😀😀");
        }
        else {
            ExtentReportManager.logFailDetails("Some of assertions failed. 😔😔😔");
        }
        String [][] finalAssertionsMap = actualValuesMap.stream().map(assertions -> new String[] {assertions.getJsonPath(),
        String.valueOf(assertions.getExpectedValue()),String.valueOf(assertions.getActualValue()),assertions.getResult()}).toArray(String[][]::new);

        Setup.extentTest.get().info(MarkupHelper.createTable(finalAssertionsMap));
    }
}
