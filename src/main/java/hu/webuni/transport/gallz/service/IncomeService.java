package hu.webuni.transport.gallz.service;

import java.util.Map.Entry;
import java.util.TreeMap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.transport.gallz.config.TransportConfigProperties;
import hu.webuni.transport.gallz.model.TransportPlan;
import hu.webuni.transport.gallz.repository.TransportPlanRepository;

@Service
public class IncomeService {
	
	@Autowired
	TransportConfigProperties config;
	
	@Autowired
	TransportPlanRepository transportPlanRepository;
	
	@Transactional
	public void adjustIncome(Long transportplanId, Integer delay) {
		TransportPlan transportPlan = transportPlanRepository.findById(transportplanId).get();
		Long currIncome = transportPlan.getIncome();
		Long newIncome = (currIncome * (100 - getIncomeDeclinePercentage(delay))) / 100;
				
		transportPlan.setIncome(newIncome);
	}
	
	private long getIncomeDeclinePercentage(int delay) {
		TreeMap<Integer, Integer> limits = config.getIncomeDeclinePercentage().getLimits();
		Entry<Integer, Integer> floorEntry = limits.floorEntry(delay);
		
		return floorEntry == null ? 0 : floorEntry.getValue();
	}
}
