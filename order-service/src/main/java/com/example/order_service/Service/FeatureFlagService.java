package com.example.order_service.Service;

import io.getunleash.Unleash;
import org.springframework.stereotype.Service;

@Service
public class FeatureFlagService {

    private final Unleash unleash;

    //Constructor injection for the Unleash client
    public FeatureFlagService(Unleash unleash) {
        this.unleash = unleash;
    }

    //Feature Flag 2 Order Notifications


    //Feature Flag 3 Bulk Order Discount
    public boolean isBulkOrderDiscountEnabled() {
       try {
           return unleash.isEnabled("bulk-order-discount", false);
       } catch (Exception e) {
           //proper error handling for Unleash connection failures
           return false;
       }
    }

}
