package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "order")
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(int id, String phoneNumber, String password, String name, List<Order> orders, Role role) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.name = name;
        this.orders = orders;
        this.role = role;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

