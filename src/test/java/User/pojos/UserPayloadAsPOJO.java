package User.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import utils.RandomDataGenerator;

import java.util.Arrays;
import java.util.stream.Stream;

@Data
// For allowing the use of default constructor when we don't want to pass all the data.
@NoArgsConstructor
// For allowing also the use of all args to be used if we decide to pass all data.
@AllArgsConstructor
// For allowing us to be able to add some custom data and the rest of as a default data.
@Builder(toBuilder = true)
// For ignoring all the data that we get in the response which we haven't sent in the request
// and don't want to assert against our request payload.
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPayloadAsPOJO {

    public static com.github.javafaker.Faker javafaker = new com.github.javafaker.Faker();
    public static net.datafaker.Faker datafaker = new net.datafaker.Faker();

//    When we want to generate value on the go using Streams.
//    private String gender = Stream.of("male","female","others").findAny().get();

//    When we want to generate value on the go using Arrays.
//    private String gender = Arrays.asList("male","female","others").get(RandomDataGenerator.getRandomNumber(0,3));

//    When we want to generate value on the go using Enum Gender.
//    private Gender gender = Arrays.stream(Gender.values()).findAny().get();

//    When we want to generate value using POJO class and pass it.
//    private Gender gender;
    private int id = Integer.parseInt(javafaker.number().digits(4));

    @ExcelCell(0)
    @JsonIgnore
    private int idValue;
    @ExcelCellName("UserName")
    private String userName = javafaker.name().username();
    @ExcelCellName("FirstName")
    private String firstName = javafaker.name().firstName();
    @ExcelCellName("LastName")
    private String lastName = javafaker.name().lastName();
    @ExcelCellName("Email")
    private String email = javafaker.internet().emailAddress();
    @ExcelCellName("Password")
    private String password = RandomStringUtils.randomAlphanumeric(8);
    @ExcelCellName("Phone")
    private String phone = datafaker.phoneNumber().cellPhone();
    private int userStatus = 0;
}
