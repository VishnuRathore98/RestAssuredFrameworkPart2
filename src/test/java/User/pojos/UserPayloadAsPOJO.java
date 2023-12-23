package User.pojos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPayloadAsPOJO {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
}
