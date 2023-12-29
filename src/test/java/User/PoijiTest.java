package User;

import User.pojos.UserPoiji;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;

import java.io.File;
import java.util.List;

public class PoijiTest {
    public static void main(String[] args) {
//      Default deliminator is a comma(,) we are changing it to semicolon(;)
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().addListDelimiter(";").build();
//      Reading data from poiji.
        List<UserPoiji> data = Poiji.fromExcel(new File("/home/vpsr/IdeaProjects/RestAssuredFrameworkPart2/src/test/resources/testdata/CreateUserDataTest.xlsx"), UserPoiji.class,options);
        System.out.println(data);
    }
}
