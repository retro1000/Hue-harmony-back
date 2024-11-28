package hueHarmony.web.specification;

import hueHarmony.web.model.Product;
import hueHarmony.web.model.ProductVariation;
import hueHarmony.web.model.Variation;
import hueHarmony.web.model.enums.data_set.ProductStatus;
import hueHarmony.web.model.Product;
import hueHarmony.web.model.enums.data_set.ProductStatus;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Set;

public class ProductSpecification {

    public static Specification<Product> hasName(String name) {
        return (root, query, cb) ->
                (name == null || name.isEmpty() || name.isBlank()) ?
                        cb.conjunction() :
                        cb.equal(root.get("productName"), name);
    }

    public static Specification<Product> hasProductStatus(Set<ProductStatus> statuses) {
        return (root, query, cb) ->
                (statuses==null || statuses.isEmpty()) ?
                        cb.conjunction() :
                        root.get("productStatus").in(statuses);
    }

    public static Specification<Product> betweenDates(LocalDate startDate, LocalDate finishDate) {
        return (root, query, cb) ->
                (startDate==null && finishDate==null) ?
                    cb.conjunction() :
                        (startDate==null ?
                            cb.lessThanOrEqualTo(root.get("productPublishedTime"), finishDate) :
                            (finishDate==null ?
                                cb.greaterThanOrEqualTo(root.get("productPublishedTime"), startDate) :
                                cb.between(root.get("productPublishedTime"), finishDate, startDate)
                            )
                        );
    }

    public static Specification<Product> betweenVariationUnitPrice(float startPrice, float finishPrice) {
        return (root, query, cb) -> {
    //        Join<Product, ProductVariation> productVariationJoin = root.join("productVariations", JoinType.RIGHT);
    //        Join<ProductVariation, Variation> variationJoin = productVariationJoin.join("variation", JoinType.RIGHT);

            return (startPrice==-1 && finishPrice==-1) ?
                cb.conjunction() :
                (startPrice==-1 ?
                    cb.lessThanOrEqualTo(root.get("unitPrice"), finishPrice) :
                    (finishPrice==-1 ?
                        cb.greaterThanOrEqualTo(root.get("productPrice"), startPrice) :
                        cb.between(root.get("unitPrice"), finishPrice, startPrice)
                    )
                );
        };
    }

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
