package net.skhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.skhu.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>  {

    Product findByName(String name);
}
