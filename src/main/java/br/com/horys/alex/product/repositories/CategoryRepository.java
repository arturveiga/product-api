package br.com.horys.alex.product.repositories;

import br.com.horys.alex.product.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
