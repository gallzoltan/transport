package hu.webuni.transport.gallz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.transport.gallz.model.Address;
import hu.webuni.transport.gallz.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;

	public Address save(Address address) {
		return addressRepository.save(address);
	}
}
