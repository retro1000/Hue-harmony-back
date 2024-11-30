package hueHarmony.web.util;

import org.springframework.context.annotation.Bean;

public class FunctionsUtil {
    public static int calculateLoyaltyPoints(double totalAmountSpent) {
        int pointsPerDollar = 1; // 1 point for every $10 spent
        return (int) (totalAmountSpent / 1000) * pointsPerDollar;
    }

    // Calculate the discount based on loyalty points
    public static double calculateDiscountFromPoints(int loyaltyPoints) {
        int pointsPerDollar = 100; // 100 points = $5
        double discountPerPoint = 300.0 / pointsPerDollar;
        return loyaltyPoints * discountPerPoint;
    }
}
