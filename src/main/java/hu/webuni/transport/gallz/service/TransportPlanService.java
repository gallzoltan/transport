package hu.webuni.transport.gallz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.transport.gallz.model.TransportPlan;
import hu.webuni.transport.gallz.repository.TransportPlanRepository;

@Service
public class TransportPlanService {

	@Autowired
	TransportPlanRepository transportplanRepository;
	
	@Autowired
	SectionService sectionService;
	
	public List<TransportPlan> getAllTransportPlans() {
		return transportplanRepository.findAll();
	}
	
	public Boolean checkExists(Long id) {
		return transportplanRepository.existsById(id);
	}
	
	public Optional<TransportPlan> findById(Long id){
		return transportplanRepository.findById(id);
	}
	
	@Transactional
	public void deleteAll() {
		sectionService.getAllSections().stream().forEach(s -> s.setTransportplan(null));
		getAllTransportPlans().stream().forEach(t -> t.setSections(null));
		transportplanRepository.deleteAll();
	}
}
