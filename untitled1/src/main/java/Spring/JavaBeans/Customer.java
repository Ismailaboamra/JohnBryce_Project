package Spring.JavaBeans;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "customers")
public class Customer {
    //id: int
    //- firstName: String
    //- lastName: String
    //- email: String
    //- password: String
    //- coupons: ArrayList<Coupon>
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @ManyToMany(mappedBy = "customers",cascade = CascadeType.ALL)
    @JoinColumn(name = "coupon_id")
    private ArrayList<Coupon> coupons;

    public Customer(int id, String firstName, String lastName, String email, String password, ArrayList<Coupon> coupons) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.coupons = coupons;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Coupon> getCoupons() {
        return coupons;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCoupons(ArrayList<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", coupons=" + coupons +
                '}'+"\n";
    }
}
