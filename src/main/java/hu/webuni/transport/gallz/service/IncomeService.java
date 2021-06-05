package hu.webuni.transport.gallz.service;

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
		Long newIncome = currIncome;
		
//		if (delay < 30) {
//			newIncome *= (100 - config.getIncomeDeclinePercentage().getFor30min()) / 100;
//		} else if (delay < 60) {
//			newIncome *= (100 - config.getIncomeDeclinePercentage().getFor60min()) / 100;
//		} else if (delay < 120) {
//			newIncome *= (100 - config.getIncomeDeclinePercentage().getFor120min()) / 100;
//		} else {
//			newIncome *= (100 - config.getIncomeDeclinePercentage().getFrom120min()) / 100;
//		}
		
		transportPlan.setIncome(newIncome);
	}
}
