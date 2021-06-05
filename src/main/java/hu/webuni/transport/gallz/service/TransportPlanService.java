package hu.webuni.transport.gallz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.transport.gallz.repository.TransportplanRepository;

@Service
public class TransportPlanService {

	@Autowired
	TransportplanRepository transportplanRepository;
	
	public Boolean checkExists(Long id) {
		return transportplanRepository.existsById(id);
	}
	
	public void findById(Long transportplanId) {
		transportplanRepository.findById(transportplanId).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST));
	}
}
