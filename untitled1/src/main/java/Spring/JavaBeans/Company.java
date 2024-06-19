package Spring.JavaBeans;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.ArrayList;

public class Company {
    //- id: int
    //- name: String
    //- email: String
    //- password: String
    //- coupons: ArrayList<Coupon>
    private int ID ;
    private String name;
    private String email;
    private String password;
    @ManyToMany(mappedBy = "customers",cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private ArrayList<Coupon> coupons;

    public Company(int ID, String name, String email, String password, ArrayList<Coupon> coupons) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.coupons = coupons;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
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

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Company{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", coupons=" + coupons +
                '}'+"\n";
    }
}
