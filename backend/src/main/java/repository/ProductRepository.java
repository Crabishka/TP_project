package repository;

import entity.Product;
import entity.ProductProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductBySizeAndProductPropertyId(double size, Long productPropertyId);
    @Query("SELECT DISTINCT p.size FROM products p")
    List<Double> findDistinctSize();

}
