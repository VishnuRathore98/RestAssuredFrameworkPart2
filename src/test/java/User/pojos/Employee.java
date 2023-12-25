// This is the Employee class on which to perform comparison.

package User.pojos;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
//import java.util.Objects;

@AllArgsConstructor
@EqualsAndHashCode
public class Employee {
    private int id;
    private String name;
    private String gender;

/*
  Constructor when we don't want to use the @AllArgsConstructor annotation from lombok.

    public Employee(int id, String name, String gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }
*/

/*
    For being able to use the equals method on our field comparisons in Employee class we need to override the equals
    method from Objects class.

    @Override
    public boolean equals(Object obj){
        Employee employee = (Employee) obj;
        return this.id == employee.id && this.name == employee.name && this.gender == employee.gender;
    }
*/

/*
    For producing same hash codes for the two objects with same fields and data we need to override the hashcode
    method from the Objects class also.

    @Override
    public int hashCode() {
        return Objects.hash(id,name,gender);
    }
*/

}
