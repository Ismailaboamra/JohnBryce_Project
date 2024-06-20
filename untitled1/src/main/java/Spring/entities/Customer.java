package Spring.entities;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Entity
public class Customer {     //class its a Definition of an object - can have a members - properties , CTR - how can we create an instance of this object , behavior - methods and functions.

    @Getter
    @Setter
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Getter
    @Setter
    @Column
    private String custName;

    @Getter
    @Setter
    @Column
    private String password;

    @ManyToMany
    private List<Coupon> coupons = new ArrayList<>();


    /***
     * CTR with no ID / Coupons
     * @param custName
     * @param password
     */
    public Customer(String custName, String password) {
        super();
        this.custName = custName;
        this.password = password;
    }


    @Override
    public String toString() {
        return "Customer [id=" + id + ", custName=" + custName + ", password=" + password + "]";
    }





}
