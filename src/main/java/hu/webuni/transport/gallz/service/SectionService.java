package hu.webuni.transport.gallz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.transport.gallz.model.Section;
import hu.webuni.transport.gallz.repository.SectionRepository;

@Service
public class SectionService {
	
	@Autowired
	SectionRepository sectionRepository;

	public Boolean checkThatMilestoneInSection(Long transportplanId, Long milestoneId) {
		List<Section> sectionsWithMilestones = sectionRepository.findByTransportAndMilestoneId(transportplanId, milestoneId);
		if(sectionsWithMilestones.equals(null))
			return false;
		else
			return true;
	}	
}
