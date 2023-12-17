package User;

import java.util.HashMap;
import java.util.Map;

public class Payloads {
    public static String getStringData(String id, String userName, String fName, String lName, String email, String pass, String phone, String uStatus){
        return "{"+
            "\"id\": "+id+","+
            "\"username\": \""+userName+"\","+
            "\"firstName\": \""+fName+"\","+
            "\"lastName\": \""+lName+"\","+
            "\"email\": \""+email+"\","+
            "\"password\": \""+pass+"\","+
            "\"phone\": \""+phone+"\","+
            "\"userStatus\": 0}";
    }
    public static Map<String,Object> getMapData(String id, String userName, String fName, String lName, String email, String pass, String phone, String uStatus){
        Map<String,Object> payload = new HashMap<>();
        payload.put("id",id);
        payload.put("username",userName);
        payload.put("firstName",fName);
        payload.put("lastName",lName);
        payload.put("email",email);
        payload.put("password",pass);
        payload.put("phone",phone);
        payload.put("userStatus",uStatus);
        return payload;
    }
}
