package hu.webuni.transport.gallz.service;

import org.springframework.data.jpa.domain.Specification;

import hu.webuni.transport.gallz.model.Address;
import hu.webuni.transport.gallz.model.Address_;

public class AddressSpecification {
	
	public static Specification<Address> hasCountry(String country){
		return (root, cq, cb) -> cb.equal(root.get(Address_.country), country);
	}
	
	public static Specification<Address> hasCity(String city){
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Address_.city)), (city + "%").toLowerCase());
	}
	
	public static Specification<Address> hasZipcode(String zipcode){
		return (root, cq, cb) -> cb.equal(root.get(Address_.zipcode), zipcode);
	}
	
	public static Specification<Address> hasStreet(String street){
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Address_.street)), (street + "%").toLowerCase());
	}
}
