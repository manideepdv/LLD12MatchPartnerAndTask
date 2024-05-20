package com.example.qcommerce.strategies;

import com.example.qcommerce.models.Partner;
import com.example.qcommerce.models.PartnerTaskMapping;
import com.example.qcommerce.models.Task;
import com.example.qcommerce.utils.DistanceUtils;

import java.util.*;
import java.util.stream.Collectors;

public class ClosestPartnerMatchingStrategy implements PartnerMatchingStrategy {
    @Override
    public List<PartnerTaskMapping> match(List<Partner> partners, List<Task> tasks) {
        // Using Java Streams
        return tasks.stream()
                .map(task -> {
                    Partner closestPartner = partners.stream()
                            .min(Comparator.comparingDouble(partner ->
                                    DistanceUtils.calculateDistance(task.getPickupLocation(), partner.getCurrentLocation())))
                            .orElse(null);

                    return new AbstractMap.SimpleEntry<>(task, closestPartner);
                })
                .filter(entry -> entry.getValue() != null)
                .collect(Collectors.groupingBy(AbstractMap.SimpleEntry::getValue))
                .entrySet().stream()
                .map(entry -> {
                    PartnerTaskMapping mapping = new PartnerTaskMapping();
                    mapping.setPartner(entry.getKey());
                    mapping.setTask(entry.getValue().get(0).getKey());
                    return mapping;
                })
                .collect(Collectors.toList());

        // Legacy Code
         /*
        List<PartnerTaskMapping> partnerTaskMappingList = new ArrayList<>();
        HashSet<String> set = new HashSet<>();


        for (Task currentTask : tasks) {
            Partner currentPartner = new Partner();
            double minDist = Double.MAX_VALUE;
            for (Partner findingPartner : partners) {
                double calculatedDist = DistanceUtils.calculateDistance(
                        currentTask.getPickupLocation(),
                        findingPartner.getCurrentLocation()
                );
                if (calculatedDist < minDist) {
                    minDist = calculatedDist;
                    currentPartner = findingPartner;
                }
            }

            String mapString = currentPartner.getName();
            if (!set.contains(mapString)) {
                set.add(mapString);
                PartnerTaskMapping matchedPartenerTask = new PartnerTaskMapping();
                matchedPartenerTask.setPartner(currentPartner);
                matchedPartenerTask.setTask(currentTask);
                partnerTaskMappingList.add(matchedPartenerTask);
            }
        }

        return partnerTaskMappingList;
         */
    }
}
