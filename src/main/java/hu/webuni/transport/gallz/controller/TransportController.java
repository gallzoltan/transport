package hu.webuni.transport.gallz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.transport.gallz.dto.DelayDto;
import hu.webuni.transport.gallz.service.DelayService;
import hu.webuni.transport.gallz.service.IncomeService;
import hu.webuni.transport.gallz.service.MilestoneService;
import hu.webuni.transport.gallz.service.SectionService;
import hu.webuni.transport.gallz.service.TransportPlanService;

@RestController
@RequestMapping("/api/transportPlans")
public class TransportController {
	
	@Autowired
	TransportPlanService transportplanService; 
	
	@Autowired
	SectionService sectionService;
	
	@Autowired
	MilestoneService milestoneService;
	
	@Autowired
	DelayService delayService;
	
	@Autowired
	IncomeService incomeService;

	@PostMapping("/{id}/delay")
	public void registerDelay(@PathVariable Long id, @RequestBody DelayDto delayDto) {
		if(transportplanService.checkExists(id) && milestoneService.checkExists(delayDto.getId())) {
			if(sectionService.checkThatMilestoneInSection(id, delayDto.getId())) {
				delayService.adjustMilestone(id, delayDto.getId(), delayDto.getDelay());
				incomeService.adjustIncome(id, delayDto.getDelay());
			}
			else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);		
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
}
