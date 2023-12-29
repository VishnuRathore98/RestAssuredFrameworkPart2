package User.pojos;


import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelUnknownCells;
import lombok.ToString;

import java.util.List;
import java.util.Map;

// To convert data reference to string
@ToString
public class UserPoiji {

    @ExcelCell(0)
    private int id;
    @ExcelCellName("UserName")
    private String userName;
    @ExcelCellName("FirstName")
    private String firstName;
    @ExcelCellName("LastName")
    private String lastName;
    @ExcelCellName("Email")
    private String email;
    @ExcelCellName("Password")
    private String password;
    @ExcelCellName("Phone")
    private List<String> phone;

//  To handle dynamically generated cells, or undefined data will return all in a map.
    @ExcelUnknownCells
    private Map<String ,String > extraCells;
}
