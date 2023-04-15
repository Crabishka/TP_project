package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @Column(name = "product_property")
    private ProductProperty productProperty;

    @Column(name = "size")
    private double size;


    @ManyToMany(mappedBy = "order_id")
    private List<Order> orders;

    public Product(int id, ProductProperty productProperty, double size) {
        this.id = id;
        this.productProperty = productProperty;
        this.size = size;
    }

    public Product() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductProperty getProductProperty() {
        return productProperty;
    }

    public void setProductProperty(ProductProperty productProperty) {
        this.productProperty = productProperty;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
