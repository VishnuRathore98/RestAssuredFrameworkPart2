package User;

import User.pojos.UserPayloadAsPOJO;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class Payloads {
    public static com.github.javafaker.Faker javafaker = new com.github.javafaker.Faker();
    public static String userName = javafaker.name().username();

    //  Updated version of java faker
    public static net.datafaker.Faker datafaker = new net.datafaker.Faker();

    public static String getStringData(){
        return "{"+
            "\"id\": "+javafaker.number().digits(4)+","+
            "\"username\": \""+userName+"\","+
            "\"firstName\": \""+javafaker.name().firstName()+"\","+
            "\"lastName\": \""+javafaker.name().lastName()+"\","+
            "\"email\": \""+javafaker.internet().emailAddress()+"\","+
            "\"password\": \""+RandomStringUtils.randomAlphanumeric(8)+"\","+
            "\"phone\": \""+datafaker.phoneNumber().cellPhone()+"\","+
            "\"userStatus\": 0}";
    }
    public static Map<String,Object> getMapData(){
        Map<String,Object> payload = new HashMap<>();
        payload.put("id",parseInt(javafaker.number().digits(4)));
        payload.put("username",userName);
        payload.put("firstName",javafaker.name().firstName());
        payload.put("lastName",javafaker.name().lastName());
        payload.put("email",javafaker.internet().emailAddress());
        payload.put("password",RandomStringUtils.randomAlphanumeric(8));
        payload.put("phone",datafaker.phoneNumber().cellPhone());
        payload.put("userStatus",0);
        return payload;
    }
    public static UserPayloadAsPOJO getCreateUserPayloadFromPOJO(){
        return UserPayloadAsPOJO.builder()
            .id(parseInt(javafaker.number().digits(4)))
            .userName(userName)
            .firstName(javafaker.name().firstName())
            .lastName(javafaker.name().lastName())
            .email(javafaker.internet().emailAddress())
            .password(RandomStringUtils.randomAlphanumeric(8))
            .phone(datafaker.phoneNumber().cellPhone())
            .userStatus(0)
            .build();
    }
}
