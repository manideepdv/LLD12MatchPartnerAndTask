package com.example.qcommerce.services;

import com.example.qcommerce.factories.PartnerMatchingStrategyFactory;
import com.example.qcommerce.models.Partner;
import com.example.qcommerce.models.PartnerTaskMapping;
import com.example.qcommerce.models.Task;
import com.example.qcommerce.repositories.PartnerRepository;
import com.example.qcommerce.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchPartnerTaskServiceImpl implements MatchPartnerTaskService {
    private final PartnerRepository partnerRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public MatchPartnerTaskServiceImpl(PartnerRepository partnerRepository, TaskRepository taskRepository) {
        this.partnerRepository = partnerRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<PartnerTaskMapping> matchPartnersAndTasks(List<Long> partnerIds, List<Long> taskIds) {
        List<Partner> partners = partnerRepository.findAllById(partnerIds);
        List<Task> tasks = taskRepository.findAllById(taskIds);

        return PartnerMatchingStrategyFactory.getPartnerMatchingStrategy().match(partners, tasks);
    }
}
