package repository;

import entity.Product;
import entity.ProductProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPropertiesRepository extends JpaRepository<ProductProperty, Long> {
}
