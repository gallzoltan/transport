package hu.webuni.transport.gallz.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.transport.gallz.model.Milestone;
import hu.webuni.transport.gallz.model.Section;
import hu.webuni.transport.gallz.repository.MilestoneRepository;

@Service
public class MilestoneService {
	
	@Autowired
	MilestoneRepository milestoneRepository;
	
	@Autowired
	SectionService sectionService;
	
	public Boolean checkExists(Long id) {
		return milestoneRepository.existsById(id);
	}

	@Transactional
	public void adjustMilestone(Long transportplanId, Long milestoneId, Integer delay) {	
		Long fromId = null;
		Long toId = null;
		Integer maxSectNumber = sectionService.findMaxSectionById(transportplanId);
		Section section = sectionService.findByMilestoneId(milestoneId).get();		
		if(section.getFromMilestone().getId() == milestoneId) {
			fromId = milestoneId;
			toId = section.getToMilestone().getId();			
		}
		if(section.getToMilestone().getId() == milestoneId) {
			if(section.getSectNumber() < maxSectNumber) {
				Integer nextSectNumber = section.getSectNumber() + 1;
				Section nextSection = sectionService.findBySectionNumber(transportplanId, nextSectNumber).get();				
				toId = milestoneId;
				fromId = nextSection.getFromMilestone().getId();
			} else {
				toId = milestoneId;
				fromId = null;
			}
		}
		if(fromId != null)
			incrementPlannedTime(fromId, delay);
		if(toId != null)
			incrementPlannedTime(toId, delay);
	}
	
	private void incrementPlannedTime(long milestoneId, int delay) {
		Milestone milestone = milestoneRepository.findById(milestoneId).get();
		milestone.setPlannedTime(milestone.getPlannedTime().plusMinutes(delay));
	}
}
