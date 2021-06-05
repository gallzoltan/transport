package hu.webuni.transport.gallz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.transport.gallz.repository.MilestoneRepository;

@Service
public class MilestoneService {
	
	@Autowired
	MilestoneRepository milestoneRepository;
	
	public Boolean checkExists(Long id) {
		return milestoneRepository.existsById(id);
	}
}
