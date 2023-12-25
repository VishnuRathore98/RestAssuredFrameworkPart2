package User.javersExamples;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Student {
    private int studentId;
    private String name;
    private List<Address> addresses;

}
