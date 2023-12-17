package User;

import utils.JsonUtils;

import java.io.IOException;
import java.util.Map;

public class Base {
    public static Map<String,Object> dataFromJsonFile;

    static {
        String env = System.getProperty("env") == null ? "QA" : System.getProperty("env");
        try{
            dataFromJsonFile = JsonUtils.getJsonDataAsMap("Users/"+env+"/UsersAPIData.json");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
