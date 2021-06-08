package hu.webuni.transport.gallz.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.transport.gallz.model.TransportPlan;
import hu.webuni.transport.gallz.repository.TransportPlanRepository;

@Service
public class TransportPlanService {

	@Autowired
	TransportPlanRepository transportplanRepository;
	
	public Boolean checkExists(Long id) {
		return transportplanRepository.existsById(id);
	}
	
	public Optional<TransportPlan> findById(Long id){
		return transportplanRepository.findById(id);
	}
}
