package hu.webuni.transport.gallz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.transport.gallz.model.Section;
import hu.webuni.transport.gallz.model.TransportPlan;
import hu.webuni.transport.gallz.dto.DelayDto;
import hu.webuni.transport.gallz.repository.MilestoneRepository;
import hu.webuni.transport.gallz.repository.SectionRepository;
import hu.webuni.transport.gallz.repository.TransportplanRepository;

@RestController
@RequestMapping("/api/transportPlan")
public class TransportController {
	
	@Autowired
	TransportplanRepository transportplanRepository; 
	
	@Autowired
	SectionRepository sectionRepository;
	
	@Autowired
	MilestoneRepository milestoneRepository;
	
	@GetMapping
	public List<Section> getAllSection(){
		return sectionRepository.findAllSection(9L);
	}

	@PostMapping("/{id}/delay")
	public void registerDelay(@PathVariable Long id, @RequestBody DelayDto delayDto) {
		if(transportplanRepository.existsById(id) && milestoneRepository.existsById(delayDto.getId())) {
			//
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
}
