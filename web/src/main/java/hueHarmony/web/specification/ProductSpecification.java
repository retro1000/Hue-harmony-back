package hueHarmony.web.specification;

import hueHarmony.web.model.Product;
import hueHarmony.web.model.enums.data_set.ProductStatus;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Set;

public class ProductSpecification {

    public static Specification<Product> hasName(String name) {
        return (root, query, cb) ->
                (name==null || name.isEmpty() || name.isBlank()) ?
                        cb.conjunction() :
                        cb.and(cb.function("MATCH", String.class, root.get("productTitle"))
                                .in(cb.literal(name)));
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
            Join<Product, ProductVariation> productVariationJoin = root.join("productVariations", JoinType.RIGHT);
            Join<ProductVariation, Variation> variationJoin = productVariationJoin.join("variation", JoinType.RIGHT);

            return (startPrice==-1 && finishPrice==-1) ?
                cb.conjunction() :
                (startPrice==-1 ?
                    cb.lessThanOrEqualTo(variationJoin.get("unitPrice"), finishPrice) :
                    (finishPrice==-1 ?
                        cb.greaterThanOrEqualTo(variationJoin.get("unitPrice"), startPrice) :
                        cb.between(root.get("unitPrice"), finishPrice, startPrice)
                    )
                );
        };
    }
}
