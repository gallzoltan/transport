package hu.webuni.transport.gallz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import hu.webuni.transport.gallz.dto.AddressFilterDto;
import hu.webuni.transport.gallz.model.Address;
import hu.webuni.transport.gallz.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;

	public List<Address> getAllAddress(){
		return addressRepository.findAll();
	}

	public Address getOneAddress(Long id) {
		return addressRepository.findAddress(id);
	}
	
	@Transactional
	public Address save(Address address) {
		return addressRepository.save(address);
	}

	@Transactional
	public Address delete(Long id) {
		addressRepository.deleteById(id);
		return null;
	}
	
	@Transactional	
	public Address update(Address address) {
		if(!addressRepository.existsById(address.getId())) {
			return null;
		} else {			
			return addressRepository.save(address);
		}
	}
	
	public Page<Address> findAddressByExample(AddressFilterDto example, Pageable pageable){
		String country = example.getCountry();
		String city = example.getCity();
		String zipcode = example.getZipcode();
		String street = example.getStreet();
		
		Specification<Address> spec = Specification.where(null);
		
		if(StringUtils.hasText(country)) {
			spec = spec.and(AddressSpecification.hasCountry(country));
		}
		
		if(StringUtils.hasText(city)) {
			spec = spec.and(AddressSpecification.hasCity(city));
		}
		
		if(StringUtils.hasText(zipcode)) {
			spec = spec.and(AddressSpecification.hasZipcode(zipcode));
		}
		
		if(StringUtils.hasText(street)) {
			spec = spec.and(AddressSpecification.hasStreet(street));
		}
		
		return addressRepository.findAll(spec, pageable);
	}
}
