package hu.webuni.transport.gallz.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.transport.gallz.model.Milestone;
import hu.webuni.transport.gallz.repository.MilestoneRepository;

@Service
public class MilestoneService {
	
	@Autowired
	MilestoneRepository milestoneRepository;
	
	public Boolean checkExists(Long id) {
		return milestoneRepository.existsById(id);
	}

	@Transactional
	public void adjustMilestone(Long id, Integer delay) {
		Milestone milestone = milestoneRepository.findById(id).get();
		milestone.setPlannedTime(milestone.getPlannedTime().plusMinutes(delay));
	}
}
