package com.example.product_service.Service;

import io.getunleash.Unleash;
import org.springframework.stereotype.Service;

@Service
public class FeatureFlagService {
    private final Unleash unleash;
    public FeatureFlagService(Unleash unleash) {
        this.unleash = unleash;
    }

    //Feature Flag 1 Premium Pricing
    public boolean isPremiumPricingEnabled(){
        try {
            return unleash.isEnabled("premium-pricing");
        }catch (Exception e){
            return false;
        }
    }
}
