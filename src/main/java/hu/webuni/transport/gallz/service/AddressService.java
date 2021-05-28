package hu.webuni.transport.gallz.service;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.transport.gallz.model.Address;
import hu.webuni.transport.gallz.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;

	@Transactional
	public Address save(Address address) {
		return addressRepository.save(address);
	}
	
	public List<Address> getAllAddress(){
		return addressRepository.findAll();
	}

	public Address getOneAddress(Long id) {
		return addressRepository.findAddress(id);
	}

	@Transactional
	public Address delete(Long id) {
		addressRepository.deleteById(id);
		return null;
	}
}
