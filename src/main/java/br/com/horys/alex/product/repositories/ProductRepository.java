package br.com.horys.alex.product.repositories;

import br.com.horys.alex.product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    String findByDescription(String description);
}