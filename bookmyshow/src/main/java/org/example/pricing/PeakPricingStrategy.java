package org.example.pricing;

public class PeakPricingStrategy implements PricingStrategy{
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice*1.2;
    }
}
