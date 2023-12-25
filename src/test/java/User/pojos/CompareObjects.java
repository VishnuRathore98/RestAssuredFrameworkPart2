// This is a test class for trying out comparison between Employee objects and their values.

package User.pojos;

import java.util.HashSet;

public class CompareObjects {
    public static void main(String[] args) {
        Employee employee1 = new Employee(1,"TestName","TestGender");
        Employee employee2 = new Employee(1,"TestName","TestGender");

        System.out.println(employee1.equals(employee2));

        HashSet set = new HashSet();
        set.add(employee1);
        set.add(employee2);

        System.out.println(set);

    }
}
