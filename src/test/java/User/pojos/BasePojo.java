package User.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePojo {

    @JsonIgnore
    private String scenarioId;
    @JsonIgnore
    private String scenarioDec;
    @JsonIgnore
    private int expectedStatusCode;
    @JsonIgnore
    private String expectedErrorMessage;

}
