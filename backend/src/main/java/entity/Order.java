package entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @Column(name="total_cost")
    private double totalCost;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",  timezone="Europe/Moscow")
    @Column(name="order_time")
    private Date orderTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm",  timezone="Europe/Moscow")
    @Column(name="start_time")
    private Date startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm",  timezone="Europe/Moscow")
    @Column(name="finish_time")
    private Date finishTime;

    @Column(name="product")
    @OneToMany(mappedBy = "product")
    private List<Product> products;

    @Column(name="status")
    private String status;

    public Order(int id, double totalCost, Date orderTime, Date startTime, Date finishTime, List<Product> products, String status) {
        this.id = id;
        this.totalCost = totalCost;
        this.orderTime = orderTime;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.products = products;
        this.status = status;
    }

    public Order() {

    }

    public int getId() {
        return id;
    }


    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
