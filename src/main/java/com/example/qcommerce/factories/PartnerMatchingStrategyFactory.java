package com.example.qcommerce.factories;

import com.example.qcommerce.strategies.ClosestPartnerMatchingStrategy;
import com.example.qcommerce.strategies.PartnerMatchingStrategy;

import java.util.List;

public class PartnerMatchingStrategyFactory {
    public static PartnerMatchingStrategy getPartnerMatchingStrategy() {
        return new ClosestPartnerMatchingStrategy();
    }
}
