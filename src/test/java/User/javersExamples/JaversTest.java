package User.javersExamples;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.ListCompareAlgorithm;

import java.util.Arrays;

public class JaversTest {
    public static void main(String[] args) {

        Address address1 = new Address();
        address1.setAddressId(100);
        address1.setAddressLine("Address Line1");

        Address address2 = new Address();
        address2.setAddressId(200);
        address2.setAddressLine("Address Line2");

        Student student1 = new Student();
        student1.setStudentId(1);
        student1.setName("John");
        student1.setAddresses(Arrays.asList(address1));

        Student student2 = new Student();
        student2.setStudentId(2);
        student2.setName("Marry");
        student2.setAddresses(Arrays.asList(address2));

//      Ignoring order of the response fields
//        Javers javers = JaversBuilder.javers().withListCompareAlgorithm(ListCompareAlgorithm.AS_SET).build();

//      Normal comparison
        Javers javers = JaversBuilder.javers().build();

        Diff diff = javers.compare(student1,student2);
        System.out.println(diff.prettyPrint());
        System.out.println(diff.getChanges());
    }
}
