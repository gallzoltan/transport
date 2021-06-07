package hu.webuni.transport.gallz.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.transport.gallz.model.Address;
import hu.webuni.transport.gallz.model.Milestone;
import hu.webuni.transport.gallz.model.Section;
import hu.webuni.transport.gallz.model.TransportPlan;
import hu.webuni.transport.gallz.repository.AddressRepository;
import hu.webuni.transport.gallz.repository.MilestoneRepository;
import hu.webuni.transport.gallz.repository.SectionRepository;
import hu.webuni.transport.gallz.repository.TransportPlanRepository;

@Service
public class InitDbService {

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	MilestoneRepository milestoneRepository;
	
	@Autowired
	SectionRepository sectionRepository;
	
	@Autowired
	TransportPlanRepository transportplanRepository;
	
	@Transactional
	public void initDb() {
		Address a1 = addressRepository.save(new Address("HU", "5630", "Békés", "Hargitta utca", "31"));
		Address a2 = addressRepository.save(new Address("HU", "2120", "Dunakeszi", "Benedek utca", "6"));
		Address a3 = addressRepository.save(new Address("HU", "1234", "asdfg", "dfrtgb utca", "44"));
		
		Milestone m1 = milestoneRepository.save(new Milestone(LocalDateTime.now(), a1));
		Milestone m2 = milestoneRepository.save(new Milestone(LocalDateTime.now().plusHours(3), a2));
		Milestone m3 = milestoneRepository.save(new Milestone(LocalDateTime.now().plusHours(4), a2));
		Milestone m4 = milestoneRepository.save(new Milestone(LocalDateTime.now().plusHours(8), a3));
		Milestone m5 = milestoneRepository.save(new Milestone(LocalDateTime.now().plusHours(1), a1));
		
		Section s1 = sectionRepository.save(new Section(1, m1, m2));
		Section s2 = sectionRepository.save(new Section(2, m3, m4));
		
		TransportPlan t1 = transportplanRepository.save(new TransportPlan());
		t1.addSection(s1);
		t1.addSection(s2);
		t1.setIncome(1000L);
	}

}
