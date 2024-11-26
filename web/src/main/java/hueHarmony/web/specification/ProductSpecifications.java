package hueHarmony.web.specification;

import hueHarmony.web.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    public static Specification<Product> withCategory(String category) {
        return (root, query, cb) ->{
        if (category == null || category.isEmpty()) {
            return cb.conjunction();
        }
         return cb.equal(root.get("category"), category);
        };


    }

    public static Specification<Product> withKeyword(String key) {
        return (root, query, criteriaBuilder) -> {
            if (key == null || key.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("key"), key);
        };
    }
}
