package com.example.qcommerce.strategies;

import com.example.qcommerce.models.Partner;
import com.example.qcommerce.models.PartnerTaskMapping;
import com.example.qcommerce.models.Task;

import java.util.List;

public interface PartnerMatchingStrategy {
    List<PartnerTaskMapping> match(List<Partner> partners, List<Task> tasks);
}
