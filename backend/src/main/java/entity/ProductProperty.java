package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "product_properties")
public class ProductProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "cost")
    private double cost;

    @Column(name = "description")
    private double description;

    @Column(name = "title")
    private String title;

    public ProductProperty(Long id, double cost, double description, String title) {
        this.id = id;
        this.cost = cost;
        this.description = description;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getDescription() {
        return description;
    }

    public void setDescription(double description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
