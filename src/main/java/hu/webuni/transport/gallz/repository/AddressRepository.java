package hu.webuni.transport.gallz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.transport.gallz.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
