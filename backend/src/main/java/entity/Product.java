package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @Column(name = "product_property")
    private double productProperty;

    @Column(name = "size")
    private double size;

    public Product(int id, double productProperty, double size) {
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

    public double getProductProperty() {
        return productProperty;
    }

    public void setProductProperty(double productProperty) {
        this.productProperty = productProperty;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
