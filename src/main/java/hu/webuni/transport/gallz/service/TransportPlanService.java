package hu.webuni.transport.gallz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.transport.gallz.model.TransportPlan;
import hu.webuni.transport.gallz.repository.TransportPlanRepository;

@Service
public class TransportPlanService {

	@Autowired
	TransportPlanRepository transportplanRepository;
	
	public Boolean checkExists(Long id) {
		return transportplanRepository.existsById(id);
	}
	
//	public void adjustMilestone(Long transportplanId, Long milestoneId, int milestoneDelay) {
//		TransportPlan transportplan = transportplanRepository.findById(transportplanId).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST));		
//	}
}
