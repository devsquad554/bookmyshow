package org.example.pricing;

public class OffPeakPricingStrategy implements PricingStrategy{
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice*0.9;
    }
}
